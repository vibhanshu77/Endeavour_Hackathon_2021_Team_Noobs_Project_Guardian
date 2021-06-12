package com.mask.memorize;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mask.memorize.Database.covidEssential;

public class Log extends AppCompatActivity {
    TextView btn,res,yog,doctor,covid;
    EditText edit1,edit2,edit3,edit4;
    Button bt;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        btn = findViewById(R.id.alarm);
        doctor=findViewById(R.id.doctor);
        yog=findViewById(R.id.yoga);
        covid=findViewById(R.id.covid);
        edit1=findViewById(R.id.edit1);
        edit2=findViewById(R.id.edit2);
        edit3=findViewById(R.id.edit3);
        edit4=findViewById(R.id.edit4);
        res=findViewById(R.id.res);
        img=findViewById(R.id.img);
        bt=findViewById(R.id.check);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bp=edit1.getText().toString().trim();
                String suger=edit2.getText().toString().trim();
                String temp=edit3.getText().toString().trim();
                String oxy=edit4.getText().toString().trim();
                if(bp.equals("")||suger.equals("")||temp.equals("")||oxy.equals(""))
                {
                    Toast.makeText(Log.this, "Please Enter Valid Data", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String ans1="";
                    int blood=Integer.parseInt(bp);
                    if(blood>120)
                    {
                        ans1+="B.P. High";
                    }
                    else if(blood>=80)
                    {
                        ans1+="B.P. Normal";
                    }
                    else
                    {
                        ans1+="B.P. Low";
                    }
                    String ans2="";
                    int sug=Integer.parseInt(suger);
                    if(sug>160)
                    {
                        ans2+="Sugar Too High";
                    }
                    else if(sug>=120)
                    {
                        ans2+="Sugar High";
                    }
                    else if(sug>=80)
                    {
                        ans2+="Sugar Normal";
                    }
                    else {
                        ans2+="Sugar Low";
                    }
                    String ans3="";
                    double tem=Double.parseDouble(temp);
                    if(tem>=98.6)
                    {
                        ans3+="Fever";
                    }
                    else if(tem>=97)
                    {
                        ans3+="No Fever";
                    }
                    else
                    {
                        ans3+="Low Fever";
                    }
                    String ans4="";
                    int o=Integer.parseInt(oxy);
                    if(o>120)
                    {
                        ans4+="Hyperoxemia";
                    }
                    else if(o>=80)
                    {
                        ans4+=" Oxygen level Normal";
                    }
                    else
                    {
                        ans4+="Hypoxemia";
                    }
                    res.setText(ans1+", "+ans2+", "+ans3+", "+ans4);
                }
            }
        });
        doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log.this, DoctorConsult.class);
                startActivity(intent);
            }
        });
        yog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log.this, YogaExercise.class);
                startActivity(intent);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log.this, alarm.class);
                startActivity(intent);
            }
        });
        covid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Log.this, covidEssential.class);
                startActivity(intent);
            }
        });
    }
}