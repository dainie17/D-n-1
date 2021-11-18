package com.example.test_du_an_mau.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test_du_an_mau.DangKyActivity;
import com.example.test_du_an_mau.DangNhapActivity;
import com.example.test_du_an_mau.QuanLySanPhamActivity;
import com.example.test_du_an_mau.R;
import com.example.test_du_an_mau.UserAcivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class fragment_them extends Fragment {

    TextView txt_SanPhamCuaToi, txt_Them_trangCaNhan, txt_DangNhap, txt_DangKy,
            txt_dangxuat;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_them, container, false);

        txt_SanPhamCuaToi = view.findViewById(R.id.txt_SanPhamCuaToi);
        txt_Them_trangCaNhan = view.findViewById(R.id.txt_Them_trangcanhan);
        txt_DangNhap = view.findViewById(R.id.txt_DangNhap);
        txt_DangKy = view.findViewById(R.id.txt_DangKy);
        txt_dangxuat = view.findViewById(R.id.txt_dangxuat);

        OnclickQuanLySanPham();
        OnclickTrangCaNhan();
        OnclickDangNhap();
        OnclickDangKy();
        OnclickDangXuat();

        return view;
    }

    private void OnclickDangXuat() {
        txt_dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                FirebaseFirestore fStore = FirebaseFirestore.getInstance();

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


}