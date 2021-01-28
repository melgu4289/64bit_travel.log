package com.melgu.travellog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

//public class RegisterActivity extends AppCompatActivity {
//
//    private static final String TAG = "RegisterActivity";
//    EditText input_email, input_password, input_password_check, mName;
//    Button btn_register;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//
////        ActionBar actionBar = getSupportActionBar();
////        actionBar.setTitle("회원가입하기");
////        actionBar.setDisplayHomeAsUpEnabled(true);
////        actionBar.setDisplayShowHomeEnabled(true);
//
//        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//
//        mName = findViewById(R.id.mName);
//        input_email = findViewById(R.id.input_email);
//        input_password = findViewById(R.id.input_password);
//        input_password_check = findViewById(R.id.input_password_check);
//        btn_register = findViewById(R.id.btn_register);
//
//        btn_register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // 가입 정보 가져오기
//                final String email = input_email.getText().toString().trim();
//                String pwd = input_password.getText().toString().trim();
//                String pwd_check = input_password_check.getText().toString().trim();
//
//                // 비밀번호와 확인용이 일치할 때
//                if (pwd.equals(pwd_check)) {
//                    Log.d(TAG, "등록 버튼" + email + " , " + pwd);
//                    final ProgressDialog mDialog = new ProgressDialog(RegisterActivity.this);
//                    mDialog.setMessage("가입중입니다...");
//                    mDialog.show();
//
//                    // 파이어베이스에 신규계정 등록
//                    firebaseAuth.createUserWithEmailAndPassword(email, pwd)
//                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
//                                @Override
//                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                    // 가입 성공시
//                                    if (task.isSuccessful()) {
//                                        mDialog.dismiss();
//
//                                        FirebaseUser user = firebaseAuth.getCurrentUser();
//
//                                        String email = user.getEmail();
//                                        String uid = user.getUid();
//                                        String name = mName.getText().toString().trim();
//
//                                        // 헤쉬맵 테이블을 파이어베이스 데이터베이스에 저장
//                                        HashMap<Object, String> hashMap = new HashMap<>();
//                                        hashMap.put("uid", uid);
//                                        hashMap.put("email", email);
//                                        hashMap.put("name", name);
//
//                                        FirebaseDatabase database = FirebaseDatabase.getInstance();
//                                        DatabaseReference reference = database.getReference("Users");
//                                        reference.child(uid).setValue(hashMap);
//
//                                        // 가입이 이루어지면 가입화면을 빠져 나옴
//                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//                                        startActivity(intent);
//                                        finish();
//                                        Toast.makeText(RegisterActivity.this, "회원가입에 성공했습니다!", Toast.LENGTH_SHORT).show();
//                                    } else {
//                                        mDialog.dismiss();
//                                        Toast.makeText(RegisterActivity.this, "아이디 시발", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                            });
//                } else {
//                    Toast.makeText(RegisterActivity.this, "비밀번호가 틀렸습니다. 다시 입력해주세요!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//}

public class RegisterActivity extends AppCompatActivity {

    private EditText email_join;
    private EditText pwd_join;
    FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email_join = (EditText) findViewById(R.id.input_email);
        pwd_join = (EditText) findViewById(R.id.input_password);


        // btn_to_login 버튼 클릭시 액티비티 이동
        Button btn_to_login = findViewById(R.id.btn_to_login);
        btn_to_login.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });




        firebaseAuth = FirebaseAuth.getInstance();
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(v -> {
            //공백인 부분을 제거하고 보여주는 trim();
            final String email = email_join.getText().toString().trim();
            final String pwd = pwd_join.getText().toString().trim();
            final String pwd_check = pwd_join.getText().toString().trim();

            if (pwd.equals(pwd_check)) {
                final ProgressDialog mDialog = new ProgressDialog(RegisterActivity.this);
                mDialog.setMessage("가입중입니다...");
                mDialog.show();

                // 파이어베이스에 신규계정 등록
                firebaseAuth.createUserWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                // 성공시
                                if (task.isSuccessful()) {
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
                                    if (user != null) {
                                        String uid = user.getUid();

                                        final FirebaseFirestore db = FirebaseFirestore.getInstance();
                                        Map<String, Object> data = new HashMap<>();
                                        data.put("travelDayFirst", "2020-10-20");
                                        data.put("travelDayLast", "2020-10-29");
                                        data.put("currentMoney", 10000);
                                        data.put("firstMoney", 12000);

                                        db.collection("userCard").document(uid).collection("cardList").document("Seoul").set(data)
                                                .addOnSuccessListener(aVoid -> {
                                                    Toast.makeText(RegisterActivity.this, "데이터 생성 성공", Toast.LENGTH_SHORT).show();
                                                })
                                                .addOnFailureListener(e -> {
                                                    Toast.makeText(RegisterActivity.this, "데이터 생성 실패", Toast.LENGTH_SHORT).show();
                                                });

                                        //                    db.collection("userCard").document(uid).set(data)
                                        //                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        //                                @Override
                                        //                                public void onSuccess(Void aVoid) {
                                        //                                    Toast.makeText(RegisterActivity.this, "성공", Toast.LENGTH_SHORT).show();
                                        //                                }
                                        //                            })
                                        //                            .addOnFailureListener(new OnFailureListener() {
                                        //                                @Override
                                        //                                public void onFailure(@NonNull Exception e) {
                                        //                                    Toast.makeText(RegisterActivity.this, "실패", Toast.LENGTH_SHORT).show();
                                        //                                }
                                        //                            });
                                    }

                                    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(RegisterActivity.this, "등록 에러", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }


        });


    }
}