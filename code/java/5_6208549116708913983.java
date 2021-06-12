package com.mask.memorize;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.mask.memorize.Database.DatabaseClass;
import com.mask.memorize.Database.EntityClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class creatEvent extends AppCompatActivity {
Button btntime,btndate,btndone;
ImageView btnrecord;
EditText edit;
String timeTo;
DatabaseClass databaseClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_event);
        btntime=findViewById(R.id.btntime);
        btndate=findViewById(R.id.btndate);
        btndone=findViewById(R.id.done);
        btnrecord=findViewById(R.id.btnreocrd);
        edit=findViewById(R.id.msg);
        databaseClass=DatabaseClass.getDatabase(getApplicationContext());
        btnrecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recordSpeech();
            }
        });
        btntime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
       selectTime();
            }
        });
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         selectDate();
            }
        });
        btndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           submit();
            }
        });
    }

    private void submit() {
        String text=edit.getText().toString().trim();
        if(text.isEmpty())
        {
           Toast.makeText(this,"Please Enter Valid Record",Toast.LENGTH_SHORT).show();
        }
        else
        {
         if(btntime.getText().toString().equals("Select Time")||btndate.getText().toString().equals("Select Date"))
         {
             Toast.makeText(this,"Please Enter Valid Record",Toast.LENGTH_SHORT).show();
         }
         else {
             String value=edit.getText().toString().trim();
             String date=btndate.getText().toString().trim();
             String time=btntime.getText().toString().trim();
             EntityClass entityClass=new EntityClass();
             entityClass.setEventdate(btndate.getText().toString().trim());
             entityClass.setEventname(edit.getText().toString().trim());
             entityClass.setEventname(btntime.getText().toString().trim());
             databaseClass.EventDao().insertAll(entityClass);
             setAlarm(value,date,time);
             finish();

         }
        }
    }

    private void selectDate() {
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                btndate.setText(day+"-"+(month+1)+"-"+year);
            }
        },year,month,day);
    datePickerDialog.show();
    }

    private void selectTime() {
        Calendar calendar=Calendar.getInstance();
        int hour=calendar.get(Calendar.HOUR);
        int min=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeTo+=i+":"+i1;
                btntime.setText(Format(i,i1));
            }
        },hour,min,false);
        timePickerDialog.show();
    }

    private String Format(int hour, int min) {
        String time;
        time="";
        String form;
        if(min/10==0)
        {
            form="0"+min;
        }
        else{
            form=""+min;
        }
        if(hour==0)
        {
            time="12"+":"+form+"A.M.";
        }
        else if(hour<12)
        {
            time=hour+":"+form+"A.M";
        }
        else if(hour==12)
        {
            time="12"+":"+form+"P.M";
        }
        else
        {
            int temp=hour-12;
            time=temp+":"+form+"P.M";
        }
        return time;
    }

    private void recordSpeech() {
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"en-US");
        try {
            startActivityForResult(intent,1);
        }catch (Exception e)
        {
            Toast.makeText(this,"Yor device is not Support",Toast.LENGTH_SHORT).show();;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1)
        {
            if(resultCode==RESULT_OK&&data!=null)
            {
                ArrayList<String> text=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                edit.setText(text.get(0));
            }
        }
    }
    private  void setAlarm(String text,String date,String time)  {
        AlarmManager am=(AlarmManager)getSystemService(Context.ALARM_SERVICE);
        Intent intent=new Intent(getApplicationContext(),AlarmBrodcast.class);
        intent.putExtra("Event",text);
        intent.putExtra("time",date);
        intent.putExtra("date",time);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(getApplicationContext(),0,intent,PendingIntent.FLAG_ONE_SHOT);
        String dateandtime=date+" "+timeTo;
        DateFormat formatter= new SimpleDateFormat("d-M-yyyy hh:mm");
       try {
           Date date1 = formatter.parse(dateandtime);
           am.set(AlarmManager.RTC_WAKEUP,date1.getTime(),pendingIntent);
       }catch (Exception e)
       {
           e.printStackTrace();
       }
       finish();
    }
}