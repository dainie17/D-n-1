package com.example.test_du_an_mau.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test_du_an_mau.Activity.LoaiChiTietCB.LoaiSPCBActivity;
import com.example.test_du_an_mau.Activity.LoaiChiTietPS.LoaiSPPSActivity;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;
import com.google.firebase.auth.FirebaseAuth;

public class ChonLoaiHinhSanPhamActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiHinhSP_NNCB, LoaiHinhSP_CSPPS;
    ImageView back;

    SanPhamDomian sanPhamDomian;

    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_loai_hinh_san_pham);

        LoaiHinhSP_NNCB = this.findViewById(R.id.LoaiHinhSP_NNCB);
        LoaiHinhSP_CSPPS = this.findViewById(R.id.LoaiHinhSP_CSPPS);
        back = this.findViewById(R.id.button_back);

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
        LoaiHinhSP_CSPPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sanPhamDomian.setLoaiHinhSP("Cac San Pham Phat Sinh");
                sanPhamDomian.setMaNguoiDung(id);

                Intent inlh = new Intent(ChonLoaiHinhSanPhamActivity.this, LoaiSPPSActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("LoaiHinhSP", sanPhamDomian);

                inlh.putExtras(bundle);

                startActivityForResult(inlh, MY_REQUEST_CODE);
            }
        });


//        Quay Lại
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}