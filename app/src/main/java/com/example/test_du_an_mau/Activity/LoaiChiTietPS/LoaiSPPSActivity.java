package com.example.test_du_an_mau.Activity.LoaiChiTietPS;

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

public class LoaiSPPSActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiSPPS_Thit, LoaiSPPS_Tieu, LoaiSPPS_Dieu, LoaiSPPS_CaCao, LoaiSPPS_Macca;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_sppsactivity);

        back = findViewById(R.id.button_back_LoaiPS);

        LoaiSPPS_Thit = this.findViewById(R.id.LoaiSPPS_Thit);
        LoaiSPPS_Tieu = this.findViewById(R.id.LoaiSPPS_Tieu);
        LoaiSPPS_Dieu = this.findViewById(R.id.LoaiSPPS_Dieu);
        LoaiSPPS_CaCao = this.findViewById(R.id.LoaiSPPS_CaCao);
        LoaiSPPS_Macca = this.findViewById(R.id.LoaiSPPS_MacCa);


        LoaiSPPS_Thit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPham = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPPSActivity.this, LoaiChiTietThitActivity.class);

                    sanPham.setLoaiSP("Thịt");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPhamPSThit", sanPham);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });

        LoaiSPPS_Tieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPham = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPPSActivity.this, LoaiChiTietPSTieuActivity.class);

                    sanPham.setLoaiSP("Bánh Mì");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPhamPSTieu", sanPham);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });

        LoaiSPPS_Dieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPham = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPPSActivity.this, LoaiChiTietPSDieuActivity.class);

                    sanPham.setLoaiSP("Bơ");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPhamPSDieu", sanPham);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);

                }

            }
        });
        LoaiSPPS_CaCao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getIntent().getExtras() != null){

                    SanPhamDomian sanPham = (SanPhamDomian) getIntent().getExtras().get("LoaiHinhSP");

                    Intent intent = new Intent(LoaiSPPSActivity.this, LoaiChiTietPSDauActivity.class);

                    sanPham.setLoaiSP("Dầu");

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("LoaiSanPhamPSDau", sanPham);

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