package com.example.test_du_an_mau.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Adapter.SanPhamMoiAdapter;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TimKiemActivity extends AppCompatActivity {

    ImageView img_BackTK, img_Search;

    AutoCompleteTextView edt_TimKiem;

    RecyclerView rscv_TimKiem;

    FirebaseDatabase database;

    DatabaseReference ref;

    List<SanPhamDomian> list;

    SanPhamMoiAdapter sanPhamMoiAdapter;

    private static final int MY_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("SanPham");

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String id = auth.getUid();

        img_BackTK = this.findViewById(R.id.img_BackTK);

        img_BackTK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        img_Search = this.findViewById(R.id.img_Search);
        edt_TimKiem = this.findViewById(R.id.edt_TimKiem);
        rscv_TimKiem = this.findViewById(R.id.rscv_TimKiem);

        //Lấy dữ liệu
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                populateTimKiem(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        //Lấy dữ liệu từ firebase
        ref.addListenerForSingleValueEvent(eventListener);

        //onClick cho nút tìm kiếm
        img_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String timKiem = edt_TimKiem.getText().toString();

                timTheoLoaiHinh(timKiem);

                timTheoLoai(timKiem);

                timTheoLoaiChiTiet(timKiem);

            }
        });

        list = new ArrayList<>();
        sanPhamMoiAdapter = new SanPhamMoiAdapter();
        rscv_TimKiem.setHasFixedSize(true);
        rscv_TimKiem.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        sanPhamMoiAdapter.setData(list, new SanPhamMoiAdapter.SanPhamOnClick() {
            @Override
            public void SpOnclick(SanPhamDomian sanPhamDomian) {

                Intent intent = new Intent(TimKiemActivity.this, ChiTietSanPhamActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DuLieuSanPham",sanPhamDomian);

                intent.putExtras(bundle);

                startActivityForResult(intent, MY_REQUEST_CODE);

            }
        });
        rscv_TimKiem.setAdapter(sanPhamMoiAdapter);

    }

    private void timTheoLoaiChiTiet(String timKiem) {
        Query loaiChiTiet = ref.orderByChild("loaiChiTietSP").startAt(timKiem).endAt(timKiem+"\uf8ff");
        loaiChiTiet.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    list.clear();
                    for (DataSnapshot dt : snapshot.getChildren()){
                        list.add(dt.getValue(SanPhamDomian.class));
                        sanPhamMoiAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void timTheoLoai(String timKiem) {
        Query query = ref.orderByChild("loaiSP").startAt(timKiem).endAt(timKiem+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    list.clear();
                    for (DataSnapshot dt : snapshot.getChildren()){
                        list.add(dt.getValue(SanPhamDomian.class));
                        sanPhamMoiAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void timTheoLoaiHinh(String timKiem) {
        Query loaiHinhSP = ref.orderByChild("loaiHinhSP").startAt(timKiem).endAt(timKiem+"\uf8ff");
        loaiHinhSP.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()){
                    list.clear();
                    for (DataSnapshot dt : snapshot.getChildren()){
                        list.add(dt.getValue(SanPhamDomian.class));
                        sanPhamMoiAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //Các dữ liệu lấy về để hiển thị mẫu
    private void populateTimKiem(DataSnapshot snapshot) {
        ArrayList<String> tudien = new ArrayList<>();

        if (snapshot.exists()){
            for (DataSnapshot sn:snapshot.getChildren()){

                String loai = sn.child("loaiSP").getValue(String.class);
                String loaiHinh = sn.child("loaiHinhSP").getValue(String.class);
                String loaiChiTiet = sn.child("loaiChiTietSP").getValue(String.class);

                    tudien.add(loai);
                    tudien.add(loaiHinh);
                    tudien.add(loaiChiTiet);

            }

            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, tudien);

            edt_TimKiem.setAdapter(adapter);


        } else {
            Log.d("SanPham", "không có dữ liệu!!");
        }

    }
}