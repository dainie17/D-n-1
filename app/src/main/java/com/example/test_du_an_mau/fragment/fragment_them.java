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
import com.example.test_du_an_mau.Activity.QuanLySanPhamActivity;
import com.example.test_du_an_mau.R;
import com.example.test_du_an_mau.Activity.UserAcivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;

public class fragment_them extends Fragment {

    TextView txt_SanPhamCuaToi, txt_Them_trangCaNhan, txt_DangNhap, txt_DangKy,
            txt_dangxuat, txt_Them_Ten, txt_emai;
    String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_them, container, false);

        txt_SanPhamCuaToi = view.findViewById(R.id.txt_SanPhamCuaToi);
        txt_Them_trangCaNhan = view.findViewById(R.id.txt_Them_trangcanhan);
        txt_DangNhap = view.findViewById(R.id.txt_DangNhap);
        txt_DangKy = view.findViewById(R.id.txt_DangKy);
        txt_dangxuat = view.findViewById(R.id.txt_dangxuat);
        txt_Them_Ten = view.findViewById(R.id.txt_Them_Ten);

        OnclickQuanLySanPham();
        OnclickTrangCaNhan();
        OnclickDangNhap();
        OnclickDangKy();
        OnclickDangXuat();
        TenNguoiDung();




        return view;
    }

    private void OnclickDangXuat() {
        txt_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                Toast.makeText(getActivity(), "Bạn đã đăng xuất", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void OnclickDangKy() {
        txt_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), DangKyActivity.class));

            }
        });
    }

    private void OnclickDangNhap() {
        txt_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), DangNhapActivity.class));
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
        FirebaseFirestore fStore = FirebaseFirestore.getInstance();
        id = mAuth.getUid();


        DocumentReference documentReference = fStore.collection("user").document(id);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                txt_Them_Ten.setText(value.getString("hoten"));
            }
        });
    }

    }

