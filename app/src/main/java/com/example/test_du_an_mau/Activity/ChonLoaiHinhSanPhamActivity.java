package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.test_du_an_mau.Activity.LoaiChiTietCB.LoaiSPCBActivity;
import com.example.test_du_an_mau.Activity.LoaiChiTietCheBien.LoaiSPDCBActivity;
import com.example.test_du_an_mau.Activity.LoaiChiTietPS.LoaiSPPSActivity;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChonLoaiHinhSanPhamActivity extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 10;
    LinearLayout LoaiHinhSP_NNCB, LoaiHinhSP_CSPPS, LoaiHinhSP_CSPDCB;
    ImageView back;

    SanPhamDomian sanPhamDomian;

    String id;
    String sdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_loai_hinh_san_pham);

        LoaiHinhSP_NNCB = this.findViewById(R.id.LoaiHinhSP_NNCB);
        LoaiHinhSP_CSPPS = this.findViewById(R.id.LoaiHinhSP_CSPPS);
        LoaiHinhSP_CSPDCB = this.findViewById(R.id.LoaiHinhSP_CSPDCB);
        back = this.findViewById(R.id.button_back);

        sanPhamDomian = new SanPhamDomian();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        id = mAuth.getUid();

        LoaiHinhSP_NNCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference = firebaseDatabase.getReference("Users");

                reference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        if (user != null){
                            sdt = user.getPhone();
                            if (sdt == null){
                                Toast.makeText(ChonLoaiHinhSanPhamActivity.this, "ko lấy đc sdt", Toast.LENGTH_SHORT).show();
                            }else {
                                sanPhamDomian.setSoDienThoai(sdt);
                            }

                        } else {
                            Toast.makeText(ChonLoaiHinhSanPhamActivity.this, "ko lấy đc số điện thoại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                sanPhamDomian.setQuyen(1);

                sanPhamDomian.setLoaiHinhSP("Nông nghiệp cơ bản");
                sanPhamDomian.setMaNguoiDung(id);

                Intent inlhsp = new Intent(ChonLoaiHinhSanPhamActivity.this, LoaiSPCBActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("LoaiHinhSP", sanPhamDomian);

                inlhsp.putExtras(bundle);

                startActivityForResult(inlhsp, MY_REQUEST_CODE);
            }
        });
        LoaiHinhSP_CSPPS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference = firebaseDatabase.getReference("Users");

                reference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        if (user != null){
                            sdt = user.getPhone();
                        } else {
                            Toast.makeText(ChonLoaiHinhSanPhamActivity.this, "ko lấy đc số điện thoại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                if (sdt == null){
                    Toast.makeText(ChonLoaiHinhSanPhamActivity.this, "ko lấy đc sdt", Toast.LENGTH_SHORT).show();
                }
                sanPhamDomian.setSoDienThoai(sdt);
                sanPhamDomian.setQuyen(1);

                sanPhamDomian.setLoaiHinhSP("Cac San Pham Phat Sinh");
                sanPhamDomian.setMaNguoiDung(id);

                Intent inlh = new Intent(ChonLoaiHinhSanPhamActivity.this, LoaiSPPSActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("LoaiHinhSP", sanPhamDomian);

                inlh.putExtras(bundle);

                startActivityForResult(inlh, MY_REQUEST_CODE);
            }
        });
        LoaiHinhSP_CSPDCB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference reference = firebaseDatabase.getReference("Users");

                reference.child(id).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        User user = snapshot.getValue(User.class);
                        if (user != null){
                            sdt = user.getPhone();
                        } else {
                            Toast.makeText(ChonLoaiHinhSanPhamActivity.this, "ko lấy đc số điện thoại", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                if (sdt == null){
                    Toast.makeText(ChonLoaiHinhSanPhamActivity.this, "ko lấy đc sdt", Toast.LENGTH_SHORT).show();
                }

                sanPhamDomian.setSoDienThoai(sdt);
                sanPhamDomian.setQuyen(1);

                sanPhamDomian.setLoaiHinhSP("Các Sản Phẩm Đã Chê Biến");
                sanPhamDomian.setMaNguoiDung(id);

                Intent inlh = new Intent(ChonLoaiHinhSanPhamActivity.this, LoaiSPDCBActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("LoaiHinhSP", sanPhamDomian);

                inlh.putExtras(bundle);

                startActivityForResult(inlh, MY_REQUEST_CODE);
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