package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.test_du_an_mau.Adapter.PhanHoiAdapter;
import com.example.test_du_an_mau.Adapter.ThongBaoAdapter;
import com.example.test_du_an_mau.Domian.PhanHoi;
import com.example.test_du_an_mau.Domian.Thongbao;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DanhSachPhanHoiActivity extends AppCompatActivity {

    ImageView img_back_dsph;
    RecyclerView rscv_PhanHoi;
    PhanHoiAdapter adapter;
    List<PhanHoi> list;
    DatabaseReference ref;

    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_phan_hoi);

        img_back_dsph = this.findViewById(R.id.img_back_dsph);
        rscv_PhanHoi = this.findViewById(R.id.rscv_PhanHoi);

        layDuLieuPhanHoi();

        list = new ArrayList<>();
        rscv_PhanHoi.setHasFixedSize(true);
        rscv_PhanHoi.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        adapter = new PhanHoiAdapter();
        adapter.setData(list);
        rscv_PhanHoi.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        rscv_PhanHoi.addItemDecoration(itemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                PhanHoi phanHoi = list.get(position);

                database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                ref = database.getReference("PhanHoi");
                ref.child(phanHoi.getId()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        itemTouchHelper.attachToRecyclerView(rscv_PhanHoi);

        img_back_dsph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void layDuLieuPhanHoi() {

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("PhanHoi");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                PhanHoi phanHoi = snapshot.getValue(PhanHoi.class);

                if (phanHoi != null){

                        list.add(phanHoi);
                        adapter.notifyDataSetChanged();

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