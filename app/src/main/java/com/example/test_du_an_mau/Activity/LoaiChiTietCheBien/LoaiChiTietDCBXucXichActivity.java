package com.example.test_du_an_mau.Activity.LoaiChiTietCheBien;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test_du_an_mau.Activity.ChonHinhAnhActivity;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;

public class LoaiChiTietDCBXucXichActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiChiTietDCBXX, LoaiChiTietDCBXX1, LoaiChiTietDCBXX2, LoaiChiTietDCBXX3;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_chi_tiet_dcbxucxich);

        back = findViewById(R.id.button_back_LoaiDCBXX);
        LoaiChiTietDCBXX = this.findViewById(R.id.LoaiChiTietDCBXX);
        LoaiChiTietDCBXX1 = this.findViewById(R.id.LoaiChiTietDCBXX1);
        LoaiChiTietDCBXX2 = this.findViewById(R.id.LoaiChiTietDCBXX2);
        LoaiChiTietDCBXX3 = this.findViewById(R.id.LoaiChiTietDCBXX3);

        LoaiChiTietDCBXX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBXX");

                    Intent intent = new Intent(LoaiChiTietDCBXucXichActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Xúc Xích Heo");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBXX1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBXX");

                    Intent intent = new Intent(LoaiChiTietDCBXucXichActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Xúc Xích Đức");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBXX2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBXX");

                    Intent intent = new Intent(LoaiChiTietDCBXucXichActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Xúc Xích Ăn Liền");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBXX3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBXX");

                    Intent intent = new Intent(LoaiChiTietDCBXucXichActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Không Xác Định");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

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