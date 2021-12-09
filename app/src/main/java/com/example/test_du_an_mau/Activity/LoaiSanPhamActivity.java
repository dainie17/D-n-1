package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test_du_an_mau.Adapter.SanPhamMoiAdapter;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class LoaiSanPhamActivity extends AppCompatActivity {

    ImageView img_BackLoai;

    TextView txt_TenLoaiLoai;

    RecyclerView rscv_SanPhamTheoLoai;

    List<SanPhamDomian> list_SanPhamMoi;

    private SanPhamMoiAdapter sanPhamAdapter;

    private FirebaseDatabase database;
    DatabaseReference ref;

    private static final int MY_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_san_pham);

        img_BackLoai = this.findViewById(R.id.img_BackLoai);
        txt_TenLoaiLoai = this.findViewById(R.id.txt_TenLoaiLoai);
        rscv_SanPhamTheoLoai = this.findViewById(R.id.rscv_SanPhamTheoLoai);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getUid();

        sanPhamAdapter = new SanPhamMoiAdapter();
        rscv_SanPhamTheoLoai.setHasFixedSize(true);
        rscv_SanPhamTheoLoai.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        getDuLieuSanPham();
        list_SanPhamMoi = new ArrayList<>();
        sanPhamAdapter.setData(list_SanPhamMoi, new SanPhamMoiAdapter.SanPhamOnClick() {
            @Override
            public void SpOnclick(SanPhamDomian sanPhamDomian) {
                Intent intent = new Intent(LoaiSanPhamActivity.this, ChiTietSanPhamActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DuLieuSanPham",sanPhamDomian);

                intent.putExtras(bundle);

                startActivityForResult(intent, MY_REQUEST_CODE);
            }

            @Override
            public void YeuThichOnclick(SanPhamDomian sanPhamDomian) {

            }

            @Override
            public void DaThichOnclick(SanPhamDomian sanPhamDomian) {

            }

            @Override
            public void KiemTraYeuThich(SanPhamDomian sanPhamDomian) {

            }
        }, id);
        rscv_SanPhamTheoLoai.setAdapter(sanPhamAdapter);

        img_BackLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void getDuLieuSanPham() {

        String loai = getIntent().getStringExtra("LoaiSP");

        txt_TenLoaiLoai.setText(loai);

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("SanPham");
        Query query = ref.orderByChild("loaiSP").equalTo(loai);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);
                if (sanPhamDomian != null){
                    list_SanPhamMoi.add(0, sanPhamDomian);
                    sanPhamAdapter.notifyDataSetChanged();
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