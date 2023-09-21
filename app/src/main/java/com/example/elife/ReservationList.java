package com.example.elife;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ReservationList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation_list);

        //上一頁
        Button buttonRBack = findViewById(R.id.ReserveBack);
        buttonRBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //健身房
        CardView buttonGym = findViewById(R.id.gymDetail);
        buttonGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationList.this, ReservationGym.class);
                ReservationList.this.startActivity(intent);
            }
        });

        //游泳池
        CardView buttonPool = findViewById(R.id.poolDetail);
        buttonPool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationList.this, ReservationPool.class);
                ReservationList.this.startActivity(intent);
            }
        });

        //KTV
        CardView buttonKTV = findViewById(R.id.ktvDetail);
        buttonKTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationList.this, ReservationKtv.class);
                ReservationList.this.startActivity(intent);
            }
        });

        //撞球
        CardView buttonBilliard = findViewById(R.id.billiardDetail);
        buttonBilliard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationList.this, ReservationBilliard.class);
                ReservationList.this.startActivity(intent);
            }
        });

        //波應是
        CardView buttonTheater = findViewById(R.id.theaterDetail);
        buttonTheater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationList.this, ReservationTheater.class);
                ReservationList.this.startActivity(intent);
            }
        });

        //紀錄
        Button buttonRecord = findViewById(R.id.recordDetail);
        buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReservationList.this, ReservationPersonal.class);
                ReservationList.this.startActivity(intent);
            }
        });
    }
}