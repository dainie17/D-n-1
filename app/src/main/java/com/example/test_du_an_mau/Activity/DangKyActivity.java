package com.example.test_du_an_mau.Activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {
    private EditText tv_emai, tv_matkhau, tv_nhaplai, tv_hoten;
    private TextView tv_dangnhapapp, tv_dangnhap;
    private Button btn_dangky;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fStore;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        tv_dangnhapapp = findViewById(R.id.tv_dangnhapapp);
        tv_dangnhap = findViewById(R.id.tv_dangnhap);
        tv_emai = findViewById(R.id.tv_email);
        tv_matkhau = findViewById(R.id.tv_matkhau);
        tv_hoten = findViewById(R.id.tv_name);
        tv_nhaplai = findViewById(R.id.tv_nhaplai);
        btn_dangky = findViewById(R.id.btn_dangky);
        tv_dangnhapapp.setOnClickListener(dangnhap());
        tv_dangnhap.setOnClickListener(dangnhap());


        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = tv_emai.getText().toString().trim();
                String pass = tv_matkhau.getText().toString().trim();
                String hoten = tv_hoten.getText().toString().trim();
                String nhaplai = tv_nhaplai.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    tv_emai.setError("Email không được bỏ trống!");
                    return;
                }
                if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                    tv_emai.setError("Email không hợp lệ");
                }

                if (TextUtils.isEmpty(pass)){
                    tv_matkhau.setError("Pass không được bỏ trống !");
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(DangKyActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            id = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = fStore.collection("user").document(id);
                            Map<String, Object> user = new HashMap<>();
                            user.put("hoten", hoten);
                            user.put("email",email);
                            user.put("matkhau", pass);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: bao"+id);
                                }
                            });
                            startActivity(new Intent(getApplicationContext(), DangNhapActivity.class));
                        }else {
                            Toast.makeText(DangKyActivity.this, "Đăng ký thất bại" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });
    }

    private View.OnClickListener dangnhap() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DangKyActivity.this, DangNhapActivity.class);
                startActivity(i);
            }

        };
    }
    }
