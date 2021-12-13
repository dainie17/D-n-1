package com.example.test_du_an_mau.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_du_an_mau.Domian.Utils;
import com.example.test_du_an_mau.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ThietLapActivity extends AppCompatActivity {
    TextView  txt_DangXuat;
    LinearLayout txt_trangcanhan;
    ImageView back;
    ProgressDialog dialog;
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
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                dialog = Utils.showLoader(ThietLapActivity.this);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("status", "offline");

                reference.updateChildren(hashMap);
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Toast.makeText(ThietLapActivity.this, "Bạn đã đăng xuất", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThietLapActivity.this, DangNhapActivity.class));
                if(dialog!=null){
                    dialog.dismiss();
                }
            }
        });
    }
}