package com.example.elife;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ReservationTheater extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_theater);

        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner1 = findViewById(R.id.spinner2);


        //上一頁
        Button buttonRBack = findViewById(R.id.theatreBack);
        buttonRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //下拉式選單
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("星期六");
        arrayList.add("星期日");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);


        //下拉式選單
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Boolean firstTime =true;
            @Override
            public void onItemSelected(AdapterView<?> parent ,View view ,int position ,long id) {
                String item = parent.getItemAtPosition(position).toString();

                if (firstTime) {
                    firstTime= false;
                } else {
                    Toast.makeText(ReservationTheater.this,"Selected Date: "+item,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //下拉式選單
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Boolean firstTime =true;
            @Override
            public void onItemSelected(AdapterView<?> parent ,View view ,int position ,long id) {
                String item = parent.getItemAtPosition(position).toString();
                if (firstTime) {
                    firstTime= false;
                } else {
                    Toast.makeText(ReservationTheater.this,"Selected Time: "+item,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayList<String> arrayList1 = new ArrayList<>();
        arrayList1.add("10:00~11:00");
        arrayList1.add("11:00~12:00");
        arrayList1.add("13:00~14:00");
        arrayList1.add("14:00~15:00");
        arrayList1.add("15:00~16:00");
        arrayList1.add("16:00~17:00");
        arrayList1.add("17:00~18:00");
        arrayList1.add("18:00~19:00");
        arrayList1.add("19:00~20:00");
        arrayList1.add("20:00~21:00");
        arrayList1.add("21:00~22:00");
        ArrayAdapter<String> adapterTime = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,arrayList1);
        adapterTime.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner1.setAdapter(adapterTime);

    }
}