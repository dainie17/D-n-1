package com.example.test_du_an_mau.Activity.LoaiChiTietCB;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.test_du_an_mau.Activity.ChonHinhAnhActivity;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;

public class LoaiChiTietDieuActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiChiTietDieu, LoaiChiTietDieu1, LoaiChiTietDieu2, LoaiChiTietDieu3, LoaiChiTietDieu4;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_chi_tiet_dieu);

        back = findViewById(R.id.button_back_Dieu);
        LoaiChiTietDieu = this.findViewById(R.id.LoaiChiTietDieu);
        LoaiChiTietDieu1 = this.findViewById(R.id.LoaiChiTietDieu1);
        LoaiChiTietDieu2 = this.findViewById(R.id.LoaiChiTietDieu2);
        LoaiChiTietDieu3 = this.findViewById(R.id.LoaiChiTietDieu3);
        LoaiChiTietDieu4 = this.findViewById(R.id.LoaiChiTietDieu4);


        //      Button loại sản Hạt Điều Nhân Trắng
        LoaiChiTietDieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDieu");

                    Intent intent = new Intent(LoaiChiTietDieuActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Hạt Điều Nhân Trắng");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });


        //      Button loại sản Hạt Điều Nhân Vỡ
        LoaiChiTietDieu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDieu");

                    Intent intent = new Intent(LoaiChiTietDieuActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Hạt Điều Nhân Vỡ");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });


        //      Button loại sản Hạt Điều Nhân Vàng
        LoaiChiTietDieu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDieu");

                    Intent intent = new Intent(LoaiChiTietDieuActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Hạt Điều Nhân Vàng");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });


        //      Button loại sản Hạt Điều BỊ Nám
        LoaiChiTietDieu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDieu");

                    Intent intent = new Intent(LoaiChiTietDieuActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Hạt Điều Nhân Bị Nám, Teo, Xâu");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });


        //      Button loại Không Xác định
        LoaiChiTietDieu4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDieu");

                    Intent intent = new Intent(LoaiChiTietDieuActivity.this, ChonHinhAnhActivity.class);

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