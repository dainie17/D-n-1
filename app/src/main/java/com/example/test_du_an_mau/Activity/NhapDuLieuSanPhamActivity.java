package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.Domian.Utils;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class NhapDuLieuSanPhamActivity extends AppCompatActivity {

    Spinner spn_ChonDonVi;

    Button btn_DangSanPham;
    ImageView back;

    TextInputLayout edt_SoLuong, edt_HanSuDung, edt_NoiSanXuat, edt_GioiHanViTri, edt_MoTaSanPham,
    edt_Gia;

    private FirebaseDatabase database;
    DatabaseReference ref;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_du_lieu_san_pham);

        back = findViewById(R.id.button_back_Them);
        spn_ChonDonVi = this.findViewById(R.id.spn_ChonDonVi);
        btn_DangSanPham = this.findViewById(R.id.btn_DangSanPham);
        edt_SoLuong = this.findViewById(R.id.edt_SoLuong);
        edt_HanSuDung = this.findViewById(R.id.edt_HanSuDung);
        edt_NoiSanXuat = this.findViewById(R.id.edt_NoiSanXuat);
        edt_GioiHanViTri = this.findViewById(R.id.edt_GioiHanViTri);
        edt_MoTaSanPham = this.findViewById(R.id.edt_MoTaSanPham);
        edt_Gia = this.findViewById(R.id.edt_Gia);

        String[] soluong = new String[]{"kg", "tấn", "tạ", "bao", "con", "cây"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplication(), R.layout.support_simple_spinner_dropdown_item, soluong);
        spn_ChonDonVi.setAdapter(arrayAdapter);

        List<String> listLinkAnh = (List<String>) getIntent().getExtras().get("LinkHinhAnh");

        btn_DangSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = Utils.showLoader(NhapDuLieuSanPhamActivity.this);

                database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                ref = database.getReference("DuyetSP");

                SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("ChonHinhAnh");

                if (sanPhamDomian != null && listLinkAnh != null) {

                    String maNguoiDang = sanPhamDomian.getMaNguoiDung();
                    String loaiHinhSanPham = sanPhamDomian.getLoaiHinhSP();
                    String loaiSanPham = sanPhamDomian.getLoaiSP();
                    String loaiChiTietSanPham = sanPhamDomian.getLoaiChiTietSP();
                    String soDienThoai = sanPhamDomian.getSoDienThoai();
                    int quyen = sanPhamDomian.getQuyen();
                    int soLuong = Integer.parseInt(edt_SoLuong.getEditText().getText().toString().trim());
                    String donVi = spn_ChonDonVi.getSelectedItem().toString().trim();
                    String hanSuDung = edt_HanSuDung.getEditText().getText().toString().trim();
                    String noiSanXuat = edt_NoiSanXuat.getEditText().getText().toString().trim();
                    String gioiHanViTri = edt_GioiHanViTri.getEditText().getText().toString().trim();
                    String moTaSanPham = edt_MoTaSanPham.getEditText().getText().toString().trim();
                    String gia = edt_Gia.getEditText().getText().toString().trim();
                    String idSanPham = ref.push().getKey();

                    SanPhamDomian sanPhamGuiLen = new SanPhamDomian();

                    sanPhamGuiLen.setMaSP(idSanPham);
                    sanPhamGuiLen.setMaNguoiDung(maNguoiDang);
                    sanPhamGuiLen.setLoaiHinhSP(loaiHinhSanPham);
                    sanPhamGuiLen.setLoaiSP(loaiSanPham);
                    sanPhamGuiLen.setLoaiChiTietSP(loaiChiTietSanPham);
                    sanPhamGuiLen.setAlbumAnh(listLinkAnh);
                    sanPhamGuiLen.setDonVi(donVi);
                    sanPhamGuiLen.setNoiSanXuat(noiSanXuat);
                    sanPhamGuiLen.setGioiHanViTri(gioiHanViTri);
                    sanPhamGuiLen.setSoLuong(soLuong);
                    sanPhamGuiLen.setHanSuDung(hanSuDung);
                    sanPhamGuiLen.setMoTaChiTiet(moTaSanPham);
                    sanPhamGuiLen.setSoDienThoai(soDienThoai);
                    sanPhamGuiLen.setGiaBan(gia);
                    sanPhamGuiLen.setQuyen(quyen);

                    ref.child(sanPhamGuiLen.getMaSP()).setValue(sanPhamGuiLen).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            if(dialog!=null){
                                dialog.dismiss();
                            }
                            Toast.makeText(NhapDuLieuSanPhamActivity.this, "Đăng sản phẩm thành công !", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(NhapDuLieuSanPhamActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                       }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(NhapDuLieuSanPhamActivity.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

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