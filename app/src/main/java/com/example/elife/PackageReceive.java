package com.example.elife;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class PackageReceive extends AppCompatActivity {

    //FirebaseAuth mAuth;
    TextView isReceiveTextView,timeTextView,packageTextView,nameTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        Button button1 = findViewById(R.id.non);
        timeTextView =findViewById(R.id.timeTextView);
        packageTextView = findViewById(R.id.packageTextView);
        nameTextView =findViewById(R.id.nameTextView);



        //上一頁
        Button buttonPBack = findViewById(R.id.PackageBack);
        buttonPBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // FirebaseUser currentUser = mAuth.getCurrentUser();

//                if(currentUser != null){
//                    System.out.println("成功");
//                    System.out.println(currentUser.getUid());
//
//
//                }


                SharedPreferences sharedPreferences = getSharedPreferences("my_prefs",Context.MODE_PRIVATE);
                //nameTextView.setText(sharedPreferences.getString("name",""));
                String uid = sharedPreferences.getString("uid","");
                retrieveFirestoreData(uid);


            }
        });
    }
    public void retrieveFirestoreData(String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection(userId).document("package");

        docRef.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()) {
                            // 获取字段的值
                            Map<String, Object> data = (Map<String, Object>) documentSnapshot.get("fgt34ty645y4w5by");

                            if (data != null && data instanceof Map) {
                                // 获取字段的子字段
                                String name = (String) data.get("who");
                                String pack = (String) data.get("thing");
                                Timestamp time = (Timestamp) data.get("arrive");
                                Boolean isReceive = (Boolean) data.get("isrecieve");

                                // 在此处使用从 Firestore 中检索到的数据来更新 Android 用户界面
                                // 例如，您可以将数据设置到 TextView、ImageView 或其他视图中
                                // 这里只是一个示例：
                                updateUIWithData(name, pack, time, isReceive);
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // 处理获取数据失败的情况
                        Toast.makeText(PackageReceive.this, "Error retrieving data: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    // 根据您的 UI 结构更新界面的方法
    private void updateUIWithData(String name, String pack, Timestamp time, Boolean isReceive) {
        // 在此处更新您的 Android 用户界面
        // 例如，将数据设置到 TextView、ImageView 或其他视图中
        // 这里只是一个示例：

        nameTextView.setText(name);
        packageTextView.setText(pack);
        timeTextView.setText(time.toString());
        //isReceiveTextView.setText(isReceive ? "Received" : "Not Received");
    }

}
