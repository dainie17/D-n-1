package com.example.test_du_an_mau.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_du_an_mau.R;
import com.google.firebase.auth.FirebaseAuth;

public class ThietLapActivity extends AppCompatActivity {
    TextView  txt_DangXuat;
    LinearLayout txt_trangcanhan;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thiet_lap);
        txt_trangcanhan = this.findViewById(R.id.txt_trangcanhan);
        txt_DangXuat = this.findViewById(R.id.txt_DangXuat);
        back = findViewById(R.id.button_back_ThietLap);

        OnclickDangXuat();
        OnclickTrangcanhan();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void OnclickTrangcanhan(){
        txt_trangcanhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThietLapActivity.this, UserAcivity.class));
            }
        });
    }
    private void OnclickDangXuat() {
        txt_DangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Toast.makeText(ThietLapActivity.this, "Bạn đã đăng xuất", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThietLapActivity.this, DangNhapActivity.class));
            }
        });
    }
}