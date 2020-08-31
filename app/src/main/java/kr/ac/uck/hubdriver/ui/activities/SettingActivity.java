package kr.ac.uck.hubdriver.ui.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import kr.ac.uck.hubdriver.R;
import kr.ac.uck.hubdriver.databinding.ActivitySettingBinding;
import kr.ac.uck.hubdriver.ui.dialogs.CarDialog;
import kr.ac.uck.hubdriver.ui.dialogs.LineDialog;
import kr.ac.uck.hubdriver.ui.dialogs.TimeDialog;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * Created by Jaehyeon on 2020/08/22.
 */
@RuntimePermissions
public class SettingActivity extends AppCompatActivity {

    private ActivitySettingBinding binding;
    private TimeDialog timeDialog;
    private LineDialog lineDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);

        binding.btnLine.setOnClickListener(v->{

            lineDialog = new LineDialog(SettingActivity.this,line -> {
                binding.tvLine.setText(line);
            });
            lineDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            lineDialog.getWindow().setGravity(Gravity.BOTTOM);
            lineDialog.show();
        });

        binding.btnTime.setOnClickListener(v->{
            timeDialog = new TimeDialog(SettingActivity.this,time -> {
               binding.tvTime.setText(time);
            });
            timeDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            timeDialog.show();
        });

        binding.btnNext.setOnClickListener(view -> {
            SettingActivityPermissionsDispatcher.showPermissionWithPermissionCheck(this);
        });
    }

    private void nextActivity(){
        if(!binding.tvLine.getText().toString().equals("") && !binding.editBusNumber.getText().toString().equals("")
        && !binding.tvTime.getText().toString().equals("") && !binding.editCarNumber.getText().toString().equals("")){
            Log.d("TAG",binding.tvTime.getText().toString());
            Intent intent = new Intent(this,ReadQRActivity.class);
            intent.putExtra("line",binding.tvLine.getText().toString());
            intent.putExtra("busNumber",binding.editBusNumber.getText().toString());
            intent.putExtra("time",binding.tvTime.getText().toString());
            intent.putExtra("car",binding.editCarNumber.getText().toString());
            startActivity(intent);
        }
        else{
            Toast.makeText(this,R.string.missing_infomation,Toast.LENGTH_SHORT).show();
        }
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void showPermission() {
        nextActivity();
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationaleForPermission(final PermissionRequest request) {
        new AlertDialog.Builder(SettingActivity.this)
                .setMessage("QR 코드 인식을 위해 카메라 사용 권한을 허용해 주세요.")
                .setPositiveButton(android.R.string.ok, (dialog, button) -> request.proceed())
                .setNegativeButton(android.R.string.cancel, (dialog, button) -> request.cancel())
                .setCancelable(false)
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void showDeniedForPermission() {
        Toast.makeText(this, "권한을 허용해 주세요.", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void showNeverAskForPermission() {
        Toast.makeText(this, "권한 허용을 해주지 않으신다면, 서비스 이용이 불가합니다.", Toast.LENGTH_SHORT).show();
    }


}
