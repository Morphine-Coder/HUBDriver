package kr.ac.uck.hubdriver.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import kr.ac.uck.hubdriver.R;
import kr.ac.uck.hubdriver.databinding.DialogTimeBinding;

/**
 * Created by Jaehyeon on 2020/08/24.
 */
public class TimeDialog extends Dialog {

    private DialogTimeBinding binding;
    private Context context;
    private TimeDialogEvent timeDialogEvent;
    private final static int TIME_PICKER_INTERVAL = 5;
    private static final DecimalFormat FORMATTER = new DecimalFormat("00");
    private NumberPicker numberPicker;

    public TimeDialog(@NonNull Context context, TimeDialogEvent timeDialogEvent) {
        super(context);
        this.context = context;
        this.timeDialogEvent = timeDialogEvent;
    }

    public interface TimeDialogEvent {
        void CustomEvent(String time); // return : 선택돤 시간
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.dialog_time, null, false);
        setContentView(binding.getRoot());

        binding.timepicker.setIs24HourView(true);
        setMinutePicker();

        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();

        params.width  = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;

        params.dimAmount=0.6f;

        params.windowAnimations = R.style.AnimationPopupStyle;
        window.setAttributes(params);

        binding.btnSetting.setOnClickListener(view -> {
            if(binding.timepicker.getHour() < 10 && getMinute() <10){
                timeDialogEvent.CustomEvent("0"+binding.timepicker.getHour()+" : 0"+getMinute());
            }
            else if(binding.timepicker.getHour() >= 10 && getMinute() <10){
                timeDialogEvent.CustomEvent(binding.timepicker.getHour()+" : 0"+getMinute());
            }
            else if(binding.timepicker.getHour() < 10 && getMinute() >=10){
                timeDialogEvent.CustomEvent("0"+binding.timepicker.getHour()+" : "+getMinute());
            }
            else{
                timeDialogEvent.CustomEvent(binding.timepicker.getHour()+" : "+getMinute());
            }
            dismiss();
        });

    }

    public void setMinutePicker() {
        int numValues = 60 / TIME_PICKER_INTERVAL;
        String[] displayedValues = new String[numValues];
        for (int i = 0; i < numValues; i++) {
            displayedValues[i] = FORMATTER.format(i * TIME_PICKER_INTERVAL);
        }

        View minute = binding.timepicker.findViewById(Resources.getSystem().getIdentifier("minute", "id", "android"));
        if ((minute != null) && (minute instanceof NumberPicker)) {
            numberPicker = (NumberPicker) minute;
            numberPicker.setMinValue(0);
            numberPicker.setMaxValue(numValues - 1);
            numberPicker.setDisplayedValues(displayedValues);
        }
    }

    public int getMinute() {
        if (numberPicker != null) {
            return (numberPicker.getValue() * TIME_PICKER_INTERVAL);
        } else {
            return binding.timepicker.getMinute();
        }
    }
//
//    @Override
//    public void onAttachedToWindow()
//    {
//        super.onAttachedToWindow();
//        try
//        {
//            Class<?> classForId = Class.forName("com.android.internal.R$id");
//            Field timePickerField = classForId.getField("timePicker");
//            this.timePicker = (TimePicker) findViewById(timePickerField.getInt(null));
//            Field mField = classForId.getField("minute");
//
//            NumberPicker mMinuteSpinner = (NumberPicker) timePicker.findViewById(mField.getInt(null));
//            mMinuteSpinner.setMinValue(0);
//            mMinuteSpinner.setMaxValue((60 / TIME_PICKER_INTERVAL) - 1);
//            List<String> displayedValues = new ArrayList<>();
//            for (int i = 0; i < 60; i += TIME_PICKER_INTERVAL)
//            {
//                displayedValues.add(String.format("%02d", i));
//            }
//            mMinuteSpinner.setDisplayedValues(displayedValues.toArray(new String[0]));
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//    }

}
