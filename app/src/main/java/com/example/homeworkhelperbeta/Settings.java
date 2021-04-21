package com.example.homeworkhelperbeta;

import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import static androidx.fragment.app.DialogFragment.STYLE_NORMAL;


public class Settings extends AppCompatActivity {

    public static void newInstance() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        final EditText editText = (EditText) findViewById(R.id.editTextTime);


        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                (MyProperties.getInstance().hour) = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                (MyProperties.getInstance().minute) = mcurrentTime.get(Calendar.MINUTE);
                int hour = (MyProperties.getInstance().hour);
                int minute = (MyProperties.getInstance().minute);

                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(Settings.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        editText.setText(selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

        }});
    }
}