package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.test_du_an_mau.Domian.PhanHoi;
import com.example.test_du_an_mau.Domian.Thongbao;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class PhanHoiActivity extends AppCompatActivity {

    ImageView img_back_ph;
    TextInputLayout edt_PhanHoi;
    Button btn_GuiPhanHoi;
    private FirebaseDatabase database;
    DatabaseReference ref;
    List<String> idAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phan_hoi);

        img_back_ph = this.findViewById(R.id.img_back_ph);
        edt_PhanHoi = this.findViewById(R.id.edt_PhanHoi);
        btn_GuiPhanHoi = this.findViewById(R.id.btn_GuiPhanHoi);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String id = firebaseUser.getUid();

        idAdmin = new ArrayList<>();

        LayIDadmin();

        img_back_ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PhanHoiActivity.this, MainActivity.class));
                finish();
            }
        });

        Thongbao thongbao = new Thongbao();

        PhanHoi phanHoi = new PhanHoi();

        thongbao.setLoaiThongBao("Thông báo");

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("Users");
        Query query = ref.orderByChild("id").equalTo(id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                User user = snapshot.getValue(User.class);

                if (user != null){

                    thongbao.setLinkAnh(user.getImageURL());
                    phanHoi.setLinkHinh(user.getImageURL());
                    phanHoi.setTenNguoiGui(user.getUsername());
                    thongbao.setNoiDung("Có phản hồi từ "+user.getUsername());

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_GuiPhanHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                ref = database.getReference("PhanHoi");

                phanHoi.setNoidung(edt_PhanHoi.getEditText().getText().toString());
                phanHoi.setId(ref.push().getKey());

                ref.child(phanHoi.getId()).setValue(phanHoi).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        startActivity(new Intent(PhanHoiActivity.this, MainActivity.class));
                    }
                });

                for (int i = 0; i < idAdmin.size(); i++){

                    ref = database.getReference("ThongBao");

                    thongbao.setIDNguoiNhan(idAdmin.get(i));
                    thongbao.setIDThongBao(ref.push().getKey());
                    ref.child(thongbao.getIDThongBao()).setValue(thongbao);
                }
            }
        });

    }

    private void LayIDadmin() {

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("Users");
        Query query = ref.orderByChild("loai").equalTo(2);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                User user = snapshot.getValue(User.class);

                if ( user != null ){

                    idAdmin.add(user.getId());

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

}