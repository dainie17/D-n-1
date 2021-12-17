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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DangNhapActivity extends AppCompatActivity {
    private TextInputLayout edt_emai, edt_matkhau;
    private TextView btn_dangnhap;
    private FirebaseAuth mAuth;

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

        tv_dangkyapp.setOnClickListener(dangky());
        tv_dangky.setOnClickListener(dangky());


        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangnhap();
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
        email = edt_emai.getEditText().getText().toString();
        String pass = edt_matkhau.getEditText().getText().toString();
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
                    //trạng thái của người dùng

                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(mAuth.getUid());

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("status", "online");

                        reference.updateChildren(hashMap);
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công ", Toast.LENGTH_SHORT).show();
                    Intent intent  = new Intent(DangNhapActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                if (e.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The email address is badly formatted.")){

                    edt_emai.setError("email sai định dạng !!");
                    edt_emai.requestFocus();
                    edt_matkhau.setError(null);

                } else if (e.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidUserException: There is no user record corresponding to this identifier. The user may have been deleted.")){

                    edt_emai.setError("email không tồn tại !!");
                    edt_emai.requestFocus();
                    edt_matkhau.setError(null);

                } else if (e.toString().equals("com.google.firebase.auth.FirebaseAuthInvalidCredentialsException: The password is invalid or the user does not have a password.")){

                    edt_matkhau.setError("sai mật khẩu !!");
                    edt_matkhau.requestFocus();
                    edt_emai.setError(null);

                }

            }
        });;

    }

}