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

public class LoaiChiTietPSDauActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiChiTietPSDau, LoaiChiTietPSDau1, LoaiChiTietPSDau2, LoaiChiTietPSDau3, LoaiChiTietPSDau4;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_chi_tiet_psdau);

        back = findViewById(R.id.button_back_PSDau);
        LoaiChiTietPSDau = this.findViewById(R.id.LoaiChiTietPSDau);
        LoaiChiTietPSDau1 = this.findViewById(R.id.LoaiChiTietPSDau1);
        LoaiChiTietPSDau2 = this.findViewById(R.id.LoaiChiTietPSDau2);
        LoaiChiTietPSDau3 = this.findViewById(R.id.LoaiChiTietPSDau3);
        LoaiChiTietPSDau4 = this.findViewById(R.id.LoaiChiTietPSDau4);

        LoaiChiTietPSDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamPSDau");

                    Intent intent = new Intent(LoaiChiTietPSDauActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Dầu Ô Liu");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietPSDau1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamPSDau");

                    Intent intent = new Intent(LoaiChiTietPSDauActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Dầu Bơ");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietPSDau2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamPSDau");

                    Intent intent = new Intent(LoaiChiTietPSDauActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Dầu Mè");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietPSDau3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamPSDau");

                    Intent intent = new Intent(LoaiChiTietPSDauActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Dầu Đậu Nành");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietPSDau4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamPSDau");

                    Intent intent = new Intent(LoaiChiTietPSDauActivity.this, ChonHinhAnhActivity.class);

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