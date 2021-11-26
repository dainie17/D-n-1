package com.example.test_du_an_mau.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class ChonLoaiHinhSanPhamActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiHinhSP_NNCB;

    SanPhamDomian sanPhamDomian;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_loai_hinh_san_pham);

        LoaiHinhSP_NNCB = this.findViewById(R.id.LoaiHinhSP_NNCB);

        sanPhamDomian = new SanPhamDomian();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        id = mAuth.getUid();

        LoaiHinhSP_NNCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sanPhamDomian.setLoaiHinhSP("Nông nghiệp cơ bản");
                sanPhamDomian.setMaNguoiDung(id);

                Intent inlhsp = new Intent(ChonLoaiHinhSanPhamActivity.this, LoaiSPCBActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("LoaiHinhSP", sanPhamDomian);

                inlhsp.putExtras(bundle);

                startActivityForResult(inlhsp, MY_REQUEST_CODE);
            }
        });

    }
}