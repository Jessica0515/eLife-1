package com.example.elife;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.apache.commons.net.ftp.FTPClient;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class Menu extends AppCompatActivity {
    TextView txtNotiMarquee, txtCalenMarquee, txtCO, txtHumidity, txtTemperature, txtAir;
    TextView txtCOTitle, txtHumidityTitle, txtTemperatureTitle, txtAirTitle;
    TextView txtCOPa, txtHumidityPa, txtTemperaturePa;
    CardView txtCOCard, txtHumidityCard, txtTemperatureCard, txtAirCard;
    Handler handlerD;
    Runnable runnableD;
    String contents;
    String AirQuality;
    FirebaseAuth auth;
    FirebaseUser firebaseUser;




    int doorCount = 0;


   // int iop = 0;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        FirebaseApp.initializeApp(Menu.this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();


        txtCO = (TextView) findViewById(R.id.statCO);
        txtHumidity = (TextView) findViewById(R.id.statHumidity);
        txtTemperature = (TextView) findViewById(R.id.statTemperature);
        txtAir = (TextView) findViewById(R.id.statAir);

        txtCOTitle = (TextView) findViewById(R.id.titleCO);
        txtHumidityTitle = (TextView) findViewById(R.id.titleHumidity);
        txtTemperatureTitle = (TextView) findViewById(R.id.titleTemperature);
        txtAirTitle = (TextView) findViewById(R.id.titleAIR);

        txtCOPa = (TextView) findViewById(R.id.paCO);
        txtHumidityPa = (TextView) findViewById(R.id.paHumidity);
        txtTemperaturePa = (TextView) findViewById(R.id.paTemperature);

        txtCOCard = (CardView) findViewById(R.id.cardCO);
        txtHumidityCard = (CardView) findViewById(R.id.cardHumidity);
        txtTemperatureCard = (CardView) findViewById(R.id.cardTemperature);
        txtAirCard = (CardView) findViewById(R.id.cardAir);

        txtNotiMarquee = (TextView) findViewById(R.id.notiMarquee);
        txtNotiMarquee.setSelected(true);

        txtCalenMarquee = (TextView) findViewById(R.id.calenMarquee);
        txtCalenMarquee.setSelected(true);

        //登出
        Button buttonLogout = findViewById(R.id.logoutButton);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(Menu.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        //公告
        RelativeLayout buttonAnnounce = findViewById(R.id.announcePage);
        buttonAnnounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, AnnounceGroup.class);
                Menu.this.startActivity(intent);
            }
        });

        //公告
        CardView buttonNotification = findViewById(R.id.notiPage);
        buttonNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, AnnouncePersonal.class);
                Menu.this.startActivity(intent);
            }
        });

        //到包裹
        Button buttonPackage = findViewById(R.id.menuPackage);
        buttonPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, PackageReceive.class);
                Menu.this.startActivity(intent);
            }
        });

        //到維修
        Button buttonRepair = findViewById(R.id.menuRepair);
        buttonRepair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, Repair.class);
                Menu.this.startActivity(intent);
            }
        });

        //到個人頁面
        Button buttonPInfo = findViewById(R.id.menuPInfo);
        buttonPInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, PersonalInformation.class);
                Menu.this.startActivity(intent);
            }
        });

        //公設預約
        Button buttonReserve = findViewById(R.id.menuReserve);
        buttonReserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, ReservationList.class);
                Menu.this.startActivity(intent);
            }
        });

        //LINE
        Button buttonLine = findViewById(R.id.menuLine);
        buttonLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
                builder.setTitle("LINE")
                        .setMessage("將重新導向至Line社團")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 用户点击了确定按钮的操作
                                Uri uri = Uri.parse("https://line.me/ti/g/_V8cvBw-nw");
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                Menu.this.startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 用户点击了取消按钮的操作
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        //FB
        Button buttonFB = findViewById(R.id.menuFB);
        buttonFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Menu.this);
                builder.setTitle("臉書")
                        .setMessage("將重新導向至臉書社團")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 用户点击了确定按钮的操作
                                Uri uri = Uri.parse("https://www.facebook.com/groups/328701943076803/?ref=share\n");
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                Menu.this.startActivity(intent);
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // 用户点击了取消按钮的操作
                            }
                        });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        Button buttonDoor = findViewById(R.id.doorButton);
        buttonDoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Menu.this, FaceDoor.class);
                Menu.this.startActivity(intent);

                System.out.println("123132123132123132123");

                String collectionPath = "user";
                String documentPath = "s_01";

// 获取对应文档的引用
                DocumentReference docRef = db.collection(collectionPath).document(documentPath);

// 获取文档数据
                docRef.get()
                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                if (documentSnapshot.exists()) {
                                    // 文档存在，获取数据
                                    String messiPassword = documentSnapshot.getString("pass");

                                    Timestamp timestamp = documentSnapshot.getTimestamp("timestamp");

                                    long timestampMillis = 0;
                                    if (timestamp != null) {
                                        timestampMillis = timestamp.toDate().getTime();
                                    }
// 初始化 SharedPreferences
                                    SharedPreferences sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE);
// 获取 SharedPreferences 编辑器
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
// 存储 long 类型的时间戳
                                    //editor.putLong("timestamp", timestampMillis);

// 提交更改
                                    //editor.apply();

                                    System.out.println(timestamp);
                                    // 现在，messiPassword 包含了 "Messi"
                                } else {
                                    // 文档不存在
                                    System.out.println("not exist");

                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // 处理获取文档数据失败的情况
                                System.out.println("FAILED");

                            }
                        });

            }
        });

        //        Firebase

        auth = FirebaseAuth.getInstance();
        TextView menuName = findViewById(R.id.menuName);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        firebaseUser = mAuth.getCurrentUser();

//        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (firebaseUser == null) {
            Intent intent = new Intent(getApplicationContext() ,Login.class);
            startActivity(intent);
            finish();
        } else {
            String uid = firebaseUser.getUid();

// 获取 SharedPreferences 编辑器
            SharedPreferences sharedPreferences = getSharedPreferences("my_prefs" ,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
// 存储 long 类型的时间戳
            editor.putString("uid" ,uid);
// 提交更改
            editor.apply();
            DocumentReference userRef = db.collection("user").document(sharedPreferences.getString("uid" ,""));
            userRef.get().addOnSuccessListener(documentSnapshot -> {
                if (documentSnapshot.exists()) {


                    String userNickName = documentSnapshot.getString("nick_name");
                    int userAddr = documentSnapshot.getLong("addr").intValue();
                    String userEmail = documentSnapshot.getString("emailaddr");
                    String username = documentSnapshot.getString("name");
                    String userPhone = documentSnapshot.getString("p_number");
                    String buildABC = "A";
                    if (userAddr / 100 == 3) {
                        buildABC = "C";
                    } else if (userAddr / 100 == 2) {
                        buildABC = "B";

                    } else {
                        buildABC = "A";

                    }
                    editor.putString("NickName" ,userNickName);
                    editor.putString("Addr" ,buildABC + "棟" + userAddr / 10 % 10 + "樓" + userAddr % 10 + "戶");
                    editor.putString("Email" ,userEmail);
                    editor.putString("Phone" ,userPhone);
                    editor.putString("name" ,username);


                    editor.apply();

                    System.out.println(sharedPreferences.getString("uid" ,"") + sharedPreferences.getString("NickName" ,""));


                    menuName.setText(username);

                } else {
                    Toast.makeText(Menu.this ,"使用者不存在， 請向管理員完成資料填寫" ,
                            Toast.LENGTH_SHORT).show();
                }
            });

        }


    //refreshFTP();

    }

    //end of onCreate
    public void refreshSensor() {
        try {
            JSONObject jObject = new JSONObject(contents);

            //CO
            String TestCO = jObject.getString("CO");
            int intCO = Integer.parseInt(TestCO);
            if (intCO > 800) {
                txtCOTitle.setTextColor(ContextCompat.getColor(this, R.color.warningWord));
                txtCO.setTextColor(ContextCompat.getColor(this, R.color.warningWord));
                txtCOCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.warning));
                txtCOPa.setTextColor(ContextCompat.getColor(this, R.color.warningWord));

            } else if (intCO > 200) {
                txtCOTitle.setTextColor(ContextCompat.getColor(this, R.color.cautionWord));
                txtCO.setTextColor(ContextCompat.getColor(this, R.color.cautionWord));
                txtCOCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.caution));
                txtCOPa.setTextColor(ContextCompat.getColor(this, R.color.cautionWord));

            } else {
                txtCOTitle.setTextColor(ContextCompat.getColor(this, R.color.safeWord));
                txtCO.setTextColor(ContextCompat.getColor(this, R.color.safeWord));
                txtCOCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.safe));
                txtCOPa.setTextColor(ContextCompat.getColor(this, R.color.safeWord));

            }
            txtCO.setText(intCO + "");

            //濕度
            String TestHum = jObject.getString("Humidity");
            int intHum = Integer.parseInt(TestHum);
            if (intHum > 70) {
                txtHumidityTitle.setTextColor(ContextCompat.getColor(this, R.color.warningWord));
                txtHumidity.setTextColor(ContextCompat.getColor(this, R.color.warningWord));
                txtHumidityCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.warning));
                txtHumidityPa.setTextColor(ContextCompat.getColor(this, R.color.warningWord));

            } else if (intHum > 50) {
                txtHumidityTitle.setTextColor(ContextCompat.getColor(this, R.color.safeWord));
                txtHumidity.setTextColor(ContextCompat.getColor(this, R.color.safeWord));
                txtHumidityCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.safe));
                txtHumidityPa.setTextColor(ContextCompat.getColor(this, R.color.safeWord));

            } else {
                txtHumidityTitle.setTextColor(ContextCompat.getColor(this, R.color.cautionWord));
                txtHumidity.setTextColor(ContextCompat.getColor(this, R.color.cautionWord));
                txtHumidityCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.caution));
                txtHumidityPa.setTextColor(ContextCompat.getColor(this, R.color.cautionWord));

            }

            txtHumidity.setText(intHum + "");

            //溫度
            String TestTemp = jObject.getString("Temperature");
            double intTemp = Double.parseDouble(TestTemp);
            if (intTemp > 35) {
                txtTemperatureTitle.setTextColor(ContextCompat.getColor(this, R.color.warningWord));
                txtTemperature.setTextColor(ContextCompat.getColor(this, R.color.warningWord));
                txtTemperatureCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.warning));
                txtTemperaturePa.setTextColor(ContextCompat.getColor(this, R.color.warningWord));
            } else if (intTemp > 18) {
                txtTemperatureTitle.setTextColor(ContextCompat.getColor(this, R.color.safeWord));
                txtTemperature.setTextColor(ContextCompat.getColor(this, R.color.safeWord));
                txtTemperatureCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.safe));
                txtTemperaturePa.setTextColor(ContextCompat.getColor(this, R.color.safeWord));
            } else {
                txtTemperatureTitle.setTextColor(ContextCompat.getColor(this, R.color.coldWord));
                txtTemperature.setTextColor(ContextCompat.getColor(this, R.color.coldWord));
                txtTemperatureCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.cold));
                txtTemperaturePa.setTextColor(ContextCompat.getColor(this, R.color.coldWord));

            }
            txtTemperature.setText(intTemp + "");

            //空氣
            String TestAir = jObject.getString("AIR");
            int intAir = Integer.parseInt(TestAir);
            if (intAir > 300) {
                txtAirTitle.setTextColor(ContextCompat.getColor(this, R.color.warningWord));
                txtAir.setTextColor(ContextCompat.getColor(this, R.color.warningWord));
                txtAirCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.warning));
                AirQuality = "危";
            } else if (intAir > 200) {
                AirQuality = "注意";
            } else {
                txtAirTitle.setTextColor(ContextCompat.getColor(this, R.color.safeWord));
                txtAir.setTextColor(ContextCompat.getColor(this, R.color.safeWord));
                txtAirCard.setCardBackgroundColor(ContextCompat.getColor(this, R.color.safe));
                AirQuality = "優";
            }
            txtAir.setText(AirQuality);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


    //https://www.youtube.com/watch?v=mAKyrMGgGU4
    public void refreshFTP() {
        TarefaDownload download = new TarefaDownload();
        download.execute();
        refreshTimeFTP(300);
    }

    private void refreshTimeFTP(int millisecond) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                refreshSensor();
                refreshFTP();
            }
        };
        handler.postDelayed(runnable, millisecond);
    }

    public void getDoor() {
        doorCount++;
        if (doorCount > 4) {
            handlerD.removeCallbacks(runnableD);
            doorCount = 0;
        } else {

            refreshDoor(3000);
        }

    }

    private void refreshDoor(int millisecond) {
        this.handlerD = new Handler();
        this.runnableD = new Runnable() {
            @Override
            public void run() {
                getDoor();
            }
        };
        handlerD.postDelayed(runnableD, millisecond);
    }


    //    https://stackoverflow.com/questions/43301170/android-how-to-get-a-string-of-txt-file-from-ftp
    private class TarefaDownload extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected Void doInBackground(Void... params) {
            FTPClient ftpClient = new FTPClient();
            try {
                ftpClient.connect("192.168.0.10", 21);
                ftpClient.enterLocalPassiveMode();
                ftpClient.login("elife", "elife");
                ftpClient.changeWorkingDirectory("/");

                InputStream inStream = ftpClient.retrieveFileStream("elife.txt");
                InputStreamReader isr = new InputStreamReader(inStream, "UTF8");


                int data = isr.read();
//                int i = 0;
                contents = "";
                while (data != 125) {
                    char theChar = (char) data;
                    contents = contents + theChar;
                    data = isr.read();
//                    i++;
//                    System.out.println(data + " " + i);
                }
                char theChar = (char) data;
                contents = contents + theChar;
                isr.close();

                ftpClient.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

        }
    }
}