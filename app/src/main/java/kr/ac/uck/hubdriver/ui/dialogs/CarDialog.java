package kr.ac.uck.hubdriver.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import kr.ac.uck.hubdriver.R;
import kr.ac.uck.hubdriver.databinding.DialogCarBinding;

/**
 * Created by Jaehyeon on 2020/08/25.
 */
public class CarDialog extends Dialog {

    private DialogCarBinding binding;
    private CustomEvent customEvent;
    private Context context;

    public CarDialog(@NonNull Context context, CustomEvent customEvent) {
        super(context);
        this.context = context;
        this.customEvent = customEvent;
    }

    public interface CustomEvent{
        void customDialogEvent(String car);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_car, null, false);

        setContentView(binding.getRoot());

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        params.width  = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        params.dimAmount=0.6f;

        params.windowAnimations = R.style.AnimationPopupStyle;
        window.setAttributes(params);

        setNumberPicker();

        binding.btnSetting.setOnClickListener(view -> {
            customEvent.customDialogEvent(binding.numberpicker.getValue()+"호차");
            dismiss();
        });
    }

    private void setNumberPicker(){
        binding.numberpicker.setMinValue(1);
        binding.numberpicker.setMaxValue(99);
        binding.numberpicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    }
}
