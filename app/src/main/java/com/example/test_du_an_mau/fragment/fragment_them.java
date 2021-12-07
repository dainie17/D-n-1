package com.example.test_du_an_mau.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test_du_an_mau.Activity.DangKyActivity;
import com.example.test_du_an_mau.Activity.DangNhapActivity;
import com.example.test_du_an_mau.Activity.NhanTinActivity;
import com.example.test_du_an_mau.Activity.QuanLySanPhamActivity;
import com.example.test_du_an_mau.Activity.ThietLapActivity;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.example.test_du_an_mau.Activity.UserAcivity;
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

public class fragment_them extends Fragment {

    TextView txt_SanPhamCuaToi, txt_Them_trangCaNhan, txt_ThietLap, txt_DangKy,
             txt_Them_Ten, txt_TinNhan;
    String id;
    DatabaseReference ref;
    private FirebaseDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_them, container, false);

        txt_SanPhamCuaToi = view.findViewById(R.id.txt_SanPhamCuaToi);
        txt_Them_trangCaNhan = view.findViewById(R.id.txt_Them_trangcanhan);
        txt_Them_Ten = view.findViewById(R.id.txt_Them_Ten);
        txt_TinNhan = view.findViewById(R.id.txt_TinNhan);
        txt_ThietLap = view.findViewById(R.id.txt_thietlap);

        OnclickQuanLySanPham();
        OnclickTrangCaNhan();
        TenNguoiDung();
        OnclickTinNhan();
        OnclickThietLap();


        return view;
    }
    private void OnclickThietLap(){
        txt_ThietLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ThietLapActivity.class));
            }
        });
    }
    private void OnclickTinNhan() {

        txt_TinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), NhanTinActivity.class);
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                id = mAuth.getUid();
                if (id==null){
                    startActivity(new Intent(getActivity(), DangNhapActivity.class));
                }
                intent.putExtra("id", id);
                startActivity(intent);

            }
        });

    }





    private void OnclickTrangCaNhan() {
        txt_Them_trangCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), UserAcivity.class));

            }
        });
    }

    private void OnclickQuanLySanPham() {
        txt_SanPhamCuaToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), QuanLySanPhamActivity.class));
            }
        });
    }
    private void TenNguoiDung(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        id = mAuth.getUid();

        if (id == null){
            return;
        }

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("Users");
        Query query = ref.orderByChild("id").equalTo(id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null){
                    txt_Them_Ten.setText(user.getUsername());
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

