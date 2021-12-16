package com.example.test_du_an_mau.Activity.LoaiChiTietPS;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test_du_an_mau.Activity.ChonHinhAnhActivity;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;

public class LoaiChiTietPSTieuActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiChiTietPSTieu, LoaiChiTietPSTieu1, LoaiChiTietPSTieu2,LoaiChiTietPSTieu3,LoaiChiTietPSTieu4;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_chi_tiet_pstieu);


        back = findViewById(R.id.button_back_PSTieu);
        LoaiChiTietPSTieu= this.findViewById(R.id.LoaiChiTietPSTieu);
        LoaiChiTietPSTieu1 = this.findViewById(R.id.LoaiChiTietPSTieu1);
        LoaiChiTietPSTieu2 = this.findViewById(R.id.LoaiChiTietPSTieu2);
        LoaiChiTietPSTieu3 = this.findViewById(R.id.LoaiChiTietPSTieu3);
        LoaiChiTietPSTieu4 = this.findViewById(R.id.LoaiChiTietPSTieu4);

        LoaiChiTietPSTieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamPSTieu");

                    Intent intent = new Intent(LoaiChiTietPSTieuActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Bánh mì Pháp");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietPSTieu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamPSTieu");

                    Intent intent = new Intent(LoaiChiTietPSTieuActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Bánh mì nướng Kaya");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietPSTieu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamPSTieu");

                    Intent intent = new Intent(LoaiChiTietPSTieuActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Bánh mì nướng Kaya");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietPSTieu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamPSTieu");

                    Intent intent = new Intent(LoaiChiTietPSTieuActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Bánh mì Mitrailette");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietPSTieu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamPSTieu");

                    Intent intent = new Intent(LoaiChiTietPSTieuActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Bánh mì Việt Nam");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietPSTieu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamPSTieu");

                    Intent intent = new Intent(LoaiChiTietPSTieuActivity.this, ChonHinhAnhActivity.class);

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