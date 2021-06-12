package com.mask.memorize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mask.memorize.Adapter.EventAdapter;
import com.mask.memorize.Database.DatabaseClass;
import com.mask.memorize.Database.EntityClass;

import org.w3c.dom.Text;

import java.util.List;

public class alarm extends AppCompatActivity {
    EventAdapter eventAdapter;
    RecyclerView recyclerView;
    DatabaseClass databaseClass;
    Button btn,bt;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        recyclerView=findViewById(R.id.recycle);
        btn=findViewById(R.id.set);
        bt=findViewById(R.id.health);
        txt=findViewById(R.id.txt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txt.setText("Your response is recorded , Now Set Your Alarm for reminder");
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(alarm.this, creatEvent.class);
                startActivity(intent);
            }
        });
        databaseClass=DatabaseClass.getDatabase(getApplicationContext());
    }
    @Override
    protected  void onResume()
    {
        super.onResume();
        setAdapter();
    }
    private void setAdapter()
    {
        List<EntityClass> classList=databaseClass.EventDao().getAllData();
        eventAdapter=new EventAdapter(getApplicationContext(),classList);
        recyclerView.setAdapter(eventAdapter);
    }
}