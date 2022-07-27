package com.example.goodnight;

import android.app.Dialog;
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


public class Setting extends AppCompatActivity {
    Button b;
    Dialog dialog;
    TimePicker tp;
    TextView tv;
    Switch ss;
    Switch vs;
    RadioGroup rg;
    int soundFlag = 1;
    int vibrateFlag = 0;
    int ringFlag;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        dialog = new Dialog(Setting.this);
        dialog.setContentView(R.layout.choose_wakeup);
        b = findViewById(R.id.button);//확인버튼
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupDialog();
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
        ss.setChecked(true);
        ss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    soundFlag = 1;
                    Toast.makeText(Setting.this, "소리 on", Toast.LENGTH_SHORT).show();
                }else{
                    soundFlag = 0;
                    Toast.makeText(Setting.this, "소리 off", Toast.LENGTH_SHORT).show();
                }
            }
        });
        vs.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    vibrateFlag = 1;
                    Toast.makeText(Setting.this, "진동 on", Toast.LENGTH_SHORT).show();
                }else{
                    vibrateFlag = 0;
                    Toast.makeText(Setting.this, "진동 off", Toast.LENGTH_SHORT).show();
                }
            }
        });

        rg = findViewById(R.id.radioGroup);

        RadioGroup.OnCheckedChangeListener radioGroupButtonChangeListener = new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioButton1) {
                    ringFlag = 1;
                    Toast.makeText(Setting.this, "sound1 선택", Toast.LENGTH_SHORT).show();
                } else if (i == R.id.radioButton2) {
                    ringFlag = 2;
                    Toast.makeText(Setting.this, "sound2 선택", Toast.LENGTH_SHORT).show();
                }
            }
        };
        rg.setOnCheckedChangeListener(radioGroupButtonChangeListener);
    }

    public void popupDialog(){
        dialog.show();
        /*
        일어날 시간 목록 중 선택하는 기능
         */
        Button checkButton = dialog.findViewById(R.id.check);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
