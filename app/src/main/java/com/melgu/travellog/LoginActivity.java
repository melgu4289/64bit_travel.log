package com.melgu.travellog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.mancj.slideup.SlideUp;
import com.mancj.slideup.SlideUpBuilder;

import org.w3c.dom.Document;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        StorageReference pathReference = storageReference.child("CountryImage/한국.jpg");

        EditText input_email = findViewById(R.id.input_email);
        EditText input_password = findViewById(R.id.input_password);
        EditText input_password_check = findViewById(R.id.input_password_check);


//        btn_to_register 버튼 클릭시 액티비티 이동
        Button btn_to_register = findViewById(R.id.btn_to_register);
        btn_to_register.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = input_email.getText().toString().trim();
                String password = input_password.getText().toString().trim();

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if (user != null) {
                                        String uid = user.getUid();
                                        DocumentReference docRef = db.collection("userCard").document(uid);
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document.exists()) {
                                                        Toast.makeText(LoginActivity.this, "데이터: " + document.getData(), Toast.LENGTH_SHORT).show();
                                                    } else {
                                                        Toast.makeText(LoginActivity.this, "존재하지 않는 doc", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }
                                        });
                                        // 로그인 성공시
                                        Intent intent = new Intent(LoginActivity.this, CardActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(LoginActivity.this, "UID 가져오기에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    Toast.makeText(LoginActivity.this, "로그인 실패! 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}