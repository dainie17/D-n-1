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

public class LoaiChiTietDCBBanhActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiChiTietDCBBanh, LoaiChiTietDCBBanh1, LoaiChiTietDCBBanh2;
    ImageView back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_chi_tiet_dcbbanh);

        back = findViewById(R.id.button_back_LoaiDCBBanh);
        LoaiChiTietDCBBanh = this.findViewById(R.id.LoaiChiTietDCBBanh);
        LoaiChiTietDCBBanh1 = this.findViewById(R.id.LoaiChiTietDCBBanh1);
        LoaiChiTietDCBBanh2 = this.findViewById(R.id.LoaiChiTietDCBBanh2);

        LoaiChiTietDCBBanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBBanh");

                    Intent intent = new Intent(LoaiChiTietDCBBanhActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Bánh Mì");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBBanh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBBanh");

                    Intent intent = new Intent(LoaiChiTietDCBBanhActivity.this, ChonHinhAnhActivity.class);

                    sanPhamDomian.setLoaiChiTietSP("Bánh Kẹo");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiChiTiet", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiChiTietDCBBanh2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiSanPhamDCBBanh");

                    Intent intent = new Intent(LoaiChiTietDCBBanhActivity.this, ChonHinhAnhActivity.class);

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