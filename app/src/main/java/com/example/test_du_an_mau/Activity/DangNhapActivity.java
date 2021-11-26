package com.example.test_du_an_mau.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DangNhapActivity extends AppCompatActivity {
    private EditText edt_emai, edt_matkhau;
    private Button btn_dangnhap;
    private FirebaseAuth mAuth;
    private ImageView btn_loginGG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);

        mAuth = FirebaseAuth.getInstance();

        TextView tv_dangkyapp = findViewById(R.id.tv_dangkyapp);
        TextView tv_dangky = findViewById(R.id.tv_dangky);
        edt_emai = findViewById(R.id.edt_emai);
        edt_matkhau = findViewById(R.id.edt_matkhau);
        btn_dangnhap = findViewById(R.id.btn_dangnhap);
        btn_loginGG = findViewById(R.id.btn_google);

        tv_dangkyapp.setOnClickListener(dangky());
        tv_dangky.setOnClickListener(dangky());


        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangnhap();
            }
        });

        btn_loginGG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private View.OnClickListener dangky() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DangNhapActivity.this, DangKyActivity.class);
                startActivity(i);
            }

        };
    }
    private void dangnhap(){  String email;
        email = edt_emai.getText().toString();
        String pass = edt_matkhau.getText().toString();
        if (TextUtils.isEmpty(email)){
            edt_emai.setError("Email không được bỏ trống");
            return;
        }
        if (TextUtils.isEmpty(pass)){
            edt_matkhau.setError("Pass không được bỏ trống");
            return;
        }
        mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công ", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(DangNhapActivity.this, MainActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Đăng nhập không thành công ", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

}