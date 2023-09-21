package com.example.elife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Repair extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repair);

        //上一頁
        Button buttonRBack = findViewById(R.id.RepairBack);
        buttonRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //上一頁
        Button buttonRRecord = findViewById(R.id.goRecord);
        buttonRRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Repair.this, RepairRecord.class);
                Repair.this.startActivity(intent);

            }
        });
    }
}