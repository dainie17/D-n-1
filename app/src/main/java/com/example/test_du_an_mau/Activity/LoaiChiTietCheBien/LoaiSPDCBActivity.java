package com.example.test_du_an_mau.Activity.LoaiChiTietCheBien;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test_du_an_mau.Activity.ChonHinhAnhActivity;
import com.example.test_du_an_mau.Activity.LoaiChiTietCB.LoaiChiTietCaPheActivity;
import com.example.test_du_an_mau.Activity.LoaiChiTietCB.LoaiChiTietDieuActivity;
import com.example.test_du_an_mau.Activity.LoaiChiTietCB.LoaiSPCBActivity;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;

public class LoaiSPDCBActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiSPDCB_Banh, LoaiSPDCB_Nuoc, LoaiSPDCB_XX, LoaiSPDCB_Ruoi, LoaiSPDCB_KXD;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_spdcbactivity);

        back = findViewById(R.id.button_back_LoaiDCB);

        LoaiSPDCB_Banh = this.findViewById(R.id.LoaiSPDCB_Banh);
        LoaiSPDCB_Nuoc = this.findViewById(R.id.LoaiSPDCB_Nuoc);
        LoaiSPDCB_XX = this.findViewById(R.id.LoaiSPDPS_XX);
        LoaiSPDCB_Ruoi = this.findViewById(R.id.LoaiSPDCB_Ruoi);
        LoaiSPDCB_KXD = this.findViewById(R.id.LoaiSPDCB_KXD);


        LoaiSPDCB_Banh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPDCBActivity.this, LoaiChiTietDCBBanhActivity.class);

                    sanPhamDomian.setLoaiSP("Bánh");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPhamDCBBanh", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });

        LoaiSPDCB_Nuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPham = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPDCBActivity.this, LoaiChiTietDCBNuoc.class);

                    sanPham.setLoaiSP("Nước Uống");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPhamDCBNuoc", sanPham);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });

        LoaiSPDCB_XX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPham = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPDCBActivity.this, LoaiChiTietDCBXucXichActivity.class);

                    sanPham.setLoaiSP("Xúc Xích");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPhamDCBXX", sanPham);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiSPDCB_Ruoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPham = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPDCBActivity.this, LoaiChiTietDCBRuoiActivity.class);

                    sanPham.setLoaiSP("Rượu");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPhamDCBRuou", sanPham);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

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