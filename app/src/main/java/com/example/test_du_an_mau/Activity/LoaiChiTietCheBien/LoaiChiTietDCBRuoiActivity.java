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

public class LoaiChiTietDCBRuoiActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiChiTietDCBRuouabc, LoaiChiTietDCBRuoi1, LoaiChiTietDCBRuoi2,LoaiChiTietDCBRuoi3, LoaiChiTietDCBRuoi4;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_chi_tiet_dcbruoi);

        back = findViewById(R.id.button_back_LoaiDCBRuou);
        LoaiChiTietDCBRuouabc = this.findViewById(R.id.LoaiChiTietDCBRuoiabc);
        LoaiChiTietDCBRuoi1 = this.findViewById(R.id.LoaiChiTietDCBRuoi1);
        LoaiChiTietDCBRuoi2 = this.findViewById(R.id.LoaiChiTietDCBRuoi2);
        LoaiChiTietDCBRuoi3 = this.findViewById(R.id.LoaiChiTietDCBRuoi3);
        LoaiChiTietDCBRuoi4 = this.findViewById(R.id.LoaiChiTietDCBRuoi4);

        LoaiChiTietDCBRuouabc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBRuou");

                    Intent intent = new Intent(LoaiChiTietDCBRuoiActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Rượi gạo Mẫu Sơn");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBRuoi1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBRuou");

                    Intent intent = new Intent(LoaiChiTietDCBRuoiActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Rượu Gò Đen");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBRuoi2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBRuou");

                    Intent intent = new Intent(LoaiChiTietDCBRuoiActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Rượu Nếp");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBRuoi3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBRuou");

                    Intent intent = new Intent(LoaiChiTietDCBRuoiActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Rượu Gạo");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBRuoi4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBRuou");

                    Intent intent = new Intent(LoaiChiTietDCBRuoiActivity.this, ChonHinhAnhActivity.class);

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