package com.example.elife;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PersonalInformation extends AppCompatActivity {
    TextView infoName, infoPhone, infoName2, infoPhone2, infoAddr, infoEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);


        //上一頁
        Button buttonPBack = findViewById(R.id.personInfoBack);
        buttonPBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);


        infoName = findViewById(R.id.infoName);
        infoPhone = findViewById(R.id.infoPhone);
        infoName2 = findViewById(R.id.infoName2);
        infoPhone2 = findViewById(R.id.infoPhone2);
        infoAddr = findViewById(R.id.infoAddr);
        infoEmail = findViewById(R.id.infoEmail);
        infoName.setText(sharedPreferences.getString("name", ""));
        infoPhone.setText(sharedPreferences.getString("Phone", ""));
        infoName2.setText(sharedPreferences.getString("name", ""));
        infoPhone2.setText(sharedPreferences.getString("Phone", ""));
        infoAddr.setText(sharedPreferences.getString("Addr", ""));
        infoEmail.setText(sharedPreferences.getString("Email", ""));

    }
}