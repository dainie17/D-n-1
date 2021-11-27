package com.example.test_du_an_mau.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;

public class LoaiSPCBActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiSP_CaPhe, LoaiSP_Tieu, LoaiSP_Dieu, LoaiSP_CaCao, LoaiSP_Macca;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_spcbactivity);

        back = findViewById(R.id.button_back_SPCB);

        LoaiSP_CaPhe = this.findViewById(R.id.LoaiSP_CaPhe);
        LoaiSP_Tieu = this.findViewById(R.id.LoaiSP_Tieu);
        LoaiSP_Dieu = this.findViewById(R.id.LoaiSP_Dieu);
        LoaiSP_CaCao = this.findViewById(R.id.LoaiSP_CaCao);
        LoaiSP_Macca = this.findViewById(R.id.LoaiSP_MacCa);


        LoaiSP_CaPhe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPCBActivity.this, LoaiChiTietCaPheActivity.class);

                    sanPhamDomian.setLoaiSP("Cà phê");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPham", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });


        LoaiSP_Tieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPCBActivity.this, LoaiChiTietTieuActivity.class);

                    sanPhamDomian.setLoaiSP("Tiêu");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPhamTieu", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });

        LoaiSP_Dieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPCBActivity.this, LoaiChiTietDieuActivity.class);

                    sanPhamDomian.setLoaiSP("Hạt Điều");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPhamDieu", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });

        LoaiSP_CaCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPCBActivity.this, LoaiChiTietCaCaoActivity.class);

                    sanPhamDomian.setLoaiSP("Ca Cao");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPhamCaCao", sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });

        LoaiSP_Macca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPCBActivity.this, LoaiChiTietMCActivity.class);

                    sanPhamDomian.setLoaiSP("MacCa");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPhamMacCa", sanPhamDomian);

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