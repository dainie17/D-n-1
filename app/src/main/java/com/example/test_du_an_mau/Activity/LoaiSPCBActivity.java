package com.example.test_du_an_mau.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;

public class LoaiSPCBActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiSP_CaPhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai_spcbactivity);

        LoaiSP_CaPhe = this.findViewById(R.id.LoaiSP_CaPhe);

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

    }
}