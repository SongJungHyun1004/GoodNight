package com.example.goodnight;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Setting extends AppCompatActivity {
    Button b;
    TimePicker tp;
    Calendar calendar;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tp = (TimePicker) findViewById(R.id.timePicker);

        calendar = Calendar.getInstance();
        int hour, min;
        if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M){
            hour = tp.getHour();
            min = tp.getMinute();
        }else{
            hour = tp.getCurrentHour();
            min = tp.getCurrentMinute();
        }
    }
}
