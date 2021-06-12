package com.mask.memorize;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mask.memorize.Adapter.EventAdapter;
import com.mask.memorize.Database.DatabaseClass;
import com.mask.memorize.Database.EntityClass;
import com.mask.memorize.Database.covidEssential;

import java.util.List;

public class MainActivity extends AppCompatActivity {
TextView btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    btn1=findViewById(R.id.btn1);
    btn1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, Log.class);
            startActivity(intent);
        }
    });

        btn2=findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Log.class);
                startActivity(intent);
            }
        });
    }
}