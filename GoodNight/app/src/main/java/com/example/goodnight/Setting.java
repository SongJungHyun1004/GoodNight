package com.example.goodnight;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Setting extends AppCompatActivity {
    Button b;
    TimePicker tp;
    TextView tv;
    Switch ss;
    Switch vs;
    RadioGroup rg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tp = findViewById(R.id.timePicker);
        tv = findViewById(R.id.timeText);

        tp.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int hour, int minute) {
                if (hour > 12) {
                    hour -= 12;
                    tv.setText("오후 " + hour + "시 " + minute + "분 선택");
                } else {
                    tv.setText("오전 " + hour + "시 " + minute + "분 선택");
                }
            }
        });

        ss = findViewById(R.id.soundSwitch);
        vs = findViewById(R.id.vibrateSwitch);
        ss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(Setting.this, "소리 on", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Setting.this, "소리 off", Toast.LENGTH_SHORT).show();
                }
            }
        });
        vs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    Toast.makeText(Setting.this, "진동 on", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Setting.this, "진동 off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rg = findViewById(R.id.radioGroup);

        RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButton1) {
                    Toast.makeText(Setting.this, "sound1 선택", Toast.LENGTH_SHORT).show();
                } else if (i == R.id.radioButton2) {
                    Toast.makeText(Setting.this, "sound2 선택", Toast.LENGTH_SHORT).show();
                }
            }
        };
        rg.setOnCheckedChangeListener(radioGroupButtonChangeListener);
    }
}
