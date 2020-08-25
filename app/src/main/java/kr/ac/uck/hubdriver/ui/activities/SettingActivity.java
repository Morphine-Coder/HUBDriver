package kr.ac.uck.hubdriver.ui.activities;

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

/**
 * Created by Jaehyeon on 2020/08/22.
 */
public class SettingActivity extends AppCompatActivity {

    private ActivitySettingBinding binding;
    private TimeDialog timeDialog;
    private LineDialog lineDialog;
    private CarDialog carDialog;

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

        binding.btnCar.setOnClickListener(view -> { // EditText 바꾸기
            carDialog = new CarDialog(SettingActivity.this, car -> {
                binding.tvCar.setText(car);
            });
            carDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            carDialog.show();
        });

        binding.btnNext.setOnClickListener(view -> {
            nextActivity();
        });
    }

    private void nextActivity(){
        if(!binding.tvLine.getText().toString().equals("") && !binding.editBusNumber.getText().toString().equals("")
        && !binding.tvTime.getText().toString().equals("") && !binding.tvCar.getText().toString().equals("")){
            Log.d("TAG",binding.tvTime.getText().toString());
            Intent intent = new Intent(this,ReadQRActivity.class);
            intent.putExtra("line",binding.tvLine.getText().toString());
            intent.putExtra("busNumber",binding.editBusNumber.getText().toString());
            intent.putExtra("time",binding.tvTime.getText().toString());
            intent.putExtra("car",binding.tvCar.getText().toString());
            startActivity(intent);
        }
        else{
            Toast.makeText(this,R.string.missing_infomation,Toast.LENGTH_SHORT).show();
        }
    }
}
