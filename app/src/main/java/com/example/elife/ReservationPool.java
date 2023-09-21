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

public class ReservationPool extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_pool);


        Spinner spinner = findViewById(R.id.spinner);
        Spinner spinner1 = findViewById(R.id.spinner2);


        //上一頁
        Button buttonRBack = findViewById(R.id.poolBack);
        buttonRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //下拉式選單
        //下拉式選單

        ArrayList<WeekDay> weekDays = new ArrayList<>();
        weekDays.add(new WeekDay("星期二", 2));
        weekDays.add(new WeekDay("星期三", 3));
        weekDays.add(new WeekDay("星期四", 4));
        weekDays.add(new WeekDay("星期五", 5));
        weekDays.add(new WeekDay("星期六", 6));
        weekDays.add(new WeekDay("星期日", 7));

        ArrayAdapter<WeekDay> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, weekDays);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Boolean firstTime = true;

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                WeekDay selectedWeekDay = (WeekDay) parent.getItemAtPosition(position);

                if (firstTime) {
                    firstTime = false;
                } else {
                    Toast.makeText(ReservationPool.this, "Selected Date: " + selectedWeekDay.getName() , Toast.LENGTH_LONG).show();
                    //回傳值
//                    int selectedValue = selectedWeekDay.getValue();
//                    Toast.makeText(ReservationBilliard.this, "Selected Date: "  + selectedValue , Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //下拉式選單
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            Boolean firstTime = true;
            @Override
            public void onItemSelected(AdapterView<?> parent ,View view ,int position ,long id) {
                TimeRange selectedTime = (TimeRange) parent.getItemAtPosition(position);
                if (firstTime) {
                    firstTime = false;
                } else {
                    Toast.makeText(ReservationPool.this, "Selected Time: " + selectedTime.getRange() , Toast.LENGTH_LONG).show();
//                    int selectedValue = selectedTime.getValue();
//                    Toast.makeText(ReservationBilliard.this, "Selected Time: "  + selectedValue , Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayList<TimeRange> timeRanges = new ArrayList<>();
        timeRanges.add(new TimeRange("10:00~11:00", 1));
        timeRanges.add(new TimeRange("11:00~12:00", 2));
        timeRanges.add(new TimeRange("13:00~14:00", 3));
        timeRanges.add(new TimeRange("14:00~15:00", 4));
        timeRanges.add(new TimeRange("15:00~16:00", 5));
        timeRanges.add(new TimeRange("16:00~17:00", 6));
        timeRanges.add(new TimeRange("17:00~18:00", 7));
        timeRanges.add(new TimeRange("18:00~19:00", 8));
        timeRanges.add(new TimeRange("19:00~20:00", 9));
        timeRanges.add(new TimeRange("20:00~21:00", 10));
        timeRanges.add(new TimeRange("21:00~22:00", 11));

        ArrayAdapter<TimeRange> time = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, timeRanges);
        time.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner1.setAdapter(time);

    }
}