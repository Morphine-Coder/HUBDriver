package kr.ac.uck.hubdriver.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import kr.ac.uck.hubdriver.R;
import kr.ac.uck.hubdriver.databinding.DialogLineBinding;

/**
 * Created by Jaehyeon on 2020/08/24.
 */
public class LineDialog extends Dialog {

    private DialogLineBinding binding;
    private Context context;
    private CustomEvent customEvent;

    public LineDialog(@NonNull Context context, CustomEvent customEvent) {
        super(context);
        this.context = context;
        this.customEvent = customEvent;
    }

    public interface CustomEvent{
        void customDialogEvent(String line);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_line,null,false);
        setContentView(binding.getRoot());

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        params.width  = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        params.dimAmount=0.6f;

        params.windowAnimations = R.style.AnimationPopupStyle;
        window.setAttributes(params);

        binding.btnAsan.setOnClickListener(v->{
            customEvent.customDialogEvent(String.valueOf(binding.tvAsan.getText()));
            dismiss();
        });

        binding.btnCheonan.setOnClickListener(v->{
            customEvent.customDialogEvent(String.valueOf(binding.tvCheonan.getText()));
            dismiss();
        });

        binding.btnOnyangstation.setOnClickListener(v->{
            customEvent.customDialogEvent(String.valueOf(binding.tvOnyangstation.getText()));
            dismiss();
        });

        binding.btnAnsanoffice.setOnClickListener(v->{
            customEvent.customDialogEvent(String.valueOf(binding.tvAsanoffice.getText()));
            dismiss();
        });

        binding.btnNorthwest.setOnClickListener(v->{
            customEvent.customDialogEvent(String.valueOf(binding.tvNorthwest.getText()));
            dismiss();
        });

        binding.btnSoutheast.setOnClickListener(view -> {
            customEvent.customDialogEvent(String.valueOf(binding.tvSoutheast.getText()));
            dismiss();
        });
    }
}
