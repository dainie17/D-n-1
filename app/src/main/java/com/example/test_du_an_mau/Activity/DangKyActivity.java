package com.example.test_du_an_mau.Activity;

import static android.content.ContentValues.TAG;

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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.Domian.Utils;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DangKyActivity extends AppCompatActivity {
    private TextInputLayout tv_emai, tv_matkhau, tv_nhaplai, tv_hoten, edt_Phone;
    private TextView tv_dangnhapapp, tv_dangnhap;
    private TextView btn_dangky;
    FirebaseAuth auth;
    DatabaseReference reference;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);

        auth = FirebaseAuth.getInstance();

        tv_dangnhapapp = findViewById(R.id.tv_dangnhapapp);
        tv_dangnhap = findViewById(R.id.tv_dangnhap);
        tv_emai = findViewById(R.id.tv_email);
        tv_matkhau = findViewById(R.id.tv_matkhau);
        tv_hoten = findViewById(R.id.tv_name);
        tv_nhaplai = findViewById(R.id.tv_nhaplai);
        btn_dangky = findViewById(R.id.btn_dangky);
        tv_dangnhapapp.setOnClickListener(dangnhap());
        tv_dangnhap.setOnClickListener(dangnhap());
        edt_Phone = findViewById(R.id.edt_Phone);


        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = tv_emai.getEditText().getText().toString().trim();
                String pass = tv_matkhau.getEditText().getText().toString().trim();
                String hoten = tv_hoten.getEditText().getText().toString().trim();
                String nhaplai = tv_nhaplai.getEditText().getText().toString().trim();
                String phone = edt_Phone.getEditText().getText().toString();

                if (TextUtils.isEmpty(hoten)){
                    tv_hoten.setError("H??? v?? t??n kh??ng ???????c ????? tr???ng !");
                }
                if (TextUtils.isEmpty(email)){
                    tv_emai.setError("Email kh??ng ???????c b??? tr???ng !");
                    return;
                }
                if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+.[a-z]+")) {
                    tv_emai.setError("Email kh??ng h???p l??? !");
                    return;
                }

                if (TextUtils.isEmpty(pass)){
                    tv_matkhau.setError("M???t kh???u kh??ng ???????c b??? tr???ng !");
                    return;
                }
                if (TextUtils.isEmpty(nhaplai)){
                    tv_nhaplai.setError("M???t kh???u kh??ng ???????c b??? tr???ng !");
                    return;
                }
                if (!nhaplai.equals(pass)){
                    tv_nhaplai.setError("M???t kh???u ph???i tr??ng nhau !");
                    return;
                }
                if (TextUtils.isEmpty(phone)){
                    edt_Phone.setError("S??? ??i???n tho???i kh??ng ???????c b??? tr???ng !");
                    return;
                }
                if (!phone.matches("(09|03|07|08|05)+([0-9]{8})")) {
                    edt_Phone.setError("S??? ??i???n tho???i kh??ng h???p l??? !");
                    return;
                }
                if (pass.length() != 6){
                    tv_matkhau.setError("M???t kh???u ph???i l???n h??n 6 !");
                }


                register(hoten, email, pass);

            }
        });
    }

    private void register(String hoten, String email, String pass) {

        dialog = Utils.showLoader(DangKyActivity.this);

        auth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();

                            reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            User user = new User();

                            String phone = edt_Phone.getEditText().getText().toString();

                            user.setId(userid);
                            user.setUsername(hoten);
                            user.setLoai(1);
                            user.setSearch(hoten.toLowerCase());
                            user.setPhone(phone);
                            user.setImageURL("default");
                            user.setStatus("offline");
                            user.setDiaChi("default");
                            user.setEmail(email);
                            user.setPass(pass);

                            if(dialog!=null){
                                dialog.dismiss();
                            }
                            reference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(DangKyActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(DangKyActivity.this, "You can't register woth this email or password", Toast.LENGTH_SHORT).show();
                            if(dialog!=null){
                                dialog.dismiss();
                            }
                        }
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
