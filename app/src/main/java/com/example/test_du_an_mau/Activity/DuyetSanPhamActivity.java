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

import com.example.test_du_an_mau.Adapter.DuyetSanPhamAdapter;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class DuyetSanPhamActivity extends AppCompatActivity {

    RecyclerView rscv_DuyetSP;
    List<SanPhamDomian> list_SanPhamMoi;
    FirebaseDatabase database;
    DatabaseReference ref;
    DuyetSanPhamAdapter duyetSanPhamAdapter;
    ImageView img_QuayLaiDuyet;
    private static final int MY_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duyet_san_pham);
        img_QuayLaiDuyet = this.findViewById(R.id.img_QuayLaiDuyet);
        rscv_DuyetSP = this.findViewById(R.id.rscv_DuyetSP);

        database = FirebaseDatabase.getInstance();
        ref = database.getReference("DuyetSP");

        duyetSanPhamAdapter = new DuyetSanPhamAdapter();
        rscv_DuyetSP.setHasFixedSize(true);
        rscv_DuyetSP.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        list_SanPhamMoi = new ArrayList<>();
        getDuLieuSanPham();
        duyetSanPhamAdapter.setData(list_SanPhamMoi, new DuyetSanPhamAdapter.SanPhamDuyetOnClick() {
            @Override
            public void SpOnclick(SanPhamDomian sanPhamDomian) {
                Intent intent = new Intent(DuyetSanPhamActivity.this, ChiTietDuyetSPActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DuLieuSanPham",sanPhamDomian);

                intent.putExtras(bundle);

                startActivityForResult(intent, MY_REQUEST_CODE);
            }
        });
        rscv_DuyetSP.setAdapter(duyetSanPhamAdapter);

    }

    private void getDuLieuSanPham() {

        Query query = ref.orderByChild("ngayThem");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);
                if (sanPhamDomian != null){
                    list_SanPhamMoi.add(0, sanPhamDomian);
                    duyetSanPhamAdapter.notifyDataSetChanged();
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

        img_QuayLaiDuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DuyetSanPhamActivity.this, MainActivity.class));
                finish();
            }
        });

    }
}