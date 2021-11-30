package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test_du_an_mau.Adapter.SanPhamCaNhanAdapter;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.List;

public class QuanLySanPhamActivity extends AppCompatActivity {

    String id;

    TextView ten_nguoi_dung;

    ImageView img_Quaylai;

    RecyclerView rscv_QuanLySanPham;

    SanPhamCaNhanAdapter sanPhamCaNhanAdapter;

    private FirebaseDatabase database;

    DatabaseReference ref;

    List<SanPhamDomian> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);

        ten_nguoi_dung = findViewById(R.id.ten_nguoi_dung);
        img_Quaylai = this.findViewById(R.id.img_QuayLai);
        rscv_QuanLySanPham = this.findViewById(R.id.rscv_QuanLySanPham);

        img_Quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sanPhamCaNhanAdapter = new SanPhamCaNhanAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rscv_QuanLySanPham.setLayoutManager(linearLayoutManager);
        getLisviewDatabasefirebase();
        list = new ArrayList<>();
        sanPhamCaNhanAdapter.setData(list, new SanPhamCaNhanAdapter.upDateClick() {
            @Override
            public void onClickUpDate(SanPhamDomian sanPhamDomian) {
                openDialogUpdate(sanPhamDomian);
            }

            @Override
            public void onClickDelete(SanPhamDomian sanPhamDomian) {
                onClickDeleteData(sanPhamDomian);
            }
        });

        rscv_QuanLySanPham.setAdapter(sanPhamCaNhanAdapter);

        TenNguoiDung();

    }

    private void openDialogUpdate(SanPhamDomian sanPhamDomian) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sua_san_pham);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
    }

    private void onClickDeleteData(SanPhamDomian sanPhamDomian) {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        aBuilder.setMessage("Bạn có chắc chắn xóa");
        aBuilder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                ref = database.getReference("SanPham");
                ref.child(sanPhamDomian.getMaSP()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            }
                        },2000);
                    }
                });
            }
        });
        aBuilder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        aBuilder.show();
    }

    private void getLisviewDatabasefirebase() {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String id = firebaseAuth.getUid();

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("SanPham");
        Query query = ref.orderByChild("maNguoiDung").equalTo(id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);
                if (sanPhamDomian != null) {
                    list.add(sanPhamDomian);
                    sanPhamCaNhanAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull   DataSnapshot snapshot, @Nullable   String previousChildName) {
                SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);
                if (sanPhamDomian == null || list == null || list.isEmpty()) {
                    return;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (sanPhamDomian.getMaSP() == list.get(i).getMaSP()) {
                        list.set(i, sanPhamDomian);
                        break;
                    }
                }
                sanPhamCaNhanAdapter.notifyDataSetChanged();
            }



            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {
                SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);
                if (sanPhamDomian == null || list == null || list.isEmpty()) {
                    return;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (sanPhamDomian.getMaSP() == list.get(i).getMaSP()) {
                        list.remove(list.get(i));
                        break;
                    }
                }
                sanPhamCaNhanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull   DataSnapshot snapshot, @Nullable   String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void TenNguoiDung(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        id = mAuth.getUid();


        DocumentReference documentReference = fStore.collection("user").document(id);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                ten_nguoi_dung.setText(value.getString("hoten"));
            }
        });
    }
}