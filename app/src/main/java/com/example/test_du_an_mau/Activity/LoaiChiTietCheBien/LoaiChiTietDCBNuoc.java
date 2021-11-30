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

public class LoaiChiTietDCBNuoc extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiChiTietDCBNuoc, LoaiChiTietDCBNuoc1, LoaiChiTietDCBNuoc2,LoaiChiTietDCBNuoc3, LoaiChiTietDCBNuoc4;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_chi_tiet_dcbnuoc);

        back = findViewById(R.id.button_back_LoaiDCBNuoc);
        LoaiChiTietDCBNuoc = this.findViewById(R.id.LoaiChiTietDCBNuoc);
        LoaiChiTietDCBNuoc1 = this.findViewById(R.id.LoaiChiTietDCBNuoc1);
        LoaiChiTietDCBNuoc2 = this.findViewById(R.id.LoaiChiTietDCBNuoc2);
        LoaiChiTietDCBNuoc3 = this.findViewById(R.id.LoaiChiTietDCBNuoc3);
        LoaiChiTietDCBNuoc4 = this.findViewById(R.id.LoaiChiTietDCBNuoc4);

        LoaiChiTietDCBNuoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBNuoc");

                    Intent intent = new Intent(LoaiChiTietDCBNuoc.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Nước Lọc");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBNuoc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBNuoc");

                    Intent intent = new Intent(LoaiChiTietDCBNuoc.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Nước Ngọt");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBNuoc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBNuoc");

                    Intent intent = new Intent(LoaiChiTietDCBNuoc.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Cà Phê");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBNuoc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBNuoc");

                    Intent intent = new Intent(LoaiChiTietDCBNuoc.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Ca Cao");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBNuoc4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBNuoc");

                    Intent intent = new Intent(LoaiChiTietDCBNuoc.this, ChonHinhAnhActivity.class);

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