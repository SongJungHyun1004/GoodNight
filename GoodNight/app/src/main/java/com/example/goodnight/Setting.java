package com.example.goodnight;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
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
    int h, m;
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
                h = hour;
                m = minute;
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
        //일어날 시간 목록 중 선택하는 기능
        m += 14;
        final String[] id = new String[5];
        for(int i = 0; i < 5; i++){
            int h1 = h;
            int m1 = m;
            int cycle = i+3;
            String ampm = "오전 ";
            m1 += 30*cycle;
            h1 += m1/60;
            m1 = m1%60;
            h1 += cycle;
            if (h1 >= 12){
                ampm = "오후 ";
                if (h1 == 12){
                }else{
                    h1 -= 12;
                    if (h1 >= 12){
                        ampm = "오전 ";
                        h1 -= 12;
                    }
                }
            }
            id[i] = cycle+" cycles : "+ ampm + h1 + "시 "+ m1 + "분";
        }
        ListView list = dialog.findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,id);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),id[i],Toast.LENGTH_SHORT).show();
            }
        });
        //확인버튼으로 팝업창 닫기
        Button checkButton = dialog.findViewById(R.id.check);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }
}
