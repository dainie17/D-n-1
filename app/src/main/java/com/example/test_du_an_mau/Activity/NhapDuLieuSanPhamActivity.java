package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.Domian.Thongbao;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.Domian.Utils;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NhapDuLieuSanPhamActivity extends AppCompatActivity {

    Spinner spn_ChonDonVi;

    TextView btn_DangSanPham;
    ImageView back;

    TextInputLayout edt_SoLuong,  edt_NoiSanXuat, edt_GioiHanViTri, edt_MoTaSanPham,
    edt_Gia;
    DatePicker edt_HanSuDung;
    private FirebaseDatabase database;
    DatabaseReference ref;
    List<String> idAdmin;
    ProgressDialog dialog;
    String id;

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


//        edt_HanSuDung.getEditText().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                chonngay();
//            }
//        });

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        id = firebaseUser.getUid();

        String[] soluong = new String[]{"kg", "tấn", "tạ", "bao", "con", "cây"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplication(), R.layout.support_simple_spinner_dropdown_item, soluong);
        spn_ChonDonVi.setAdapter(arrayAdapter);
        idAdmin = new ArrayList<>();
        List<String> listLinkAnh = (List<String>) getIntent().getExtras().get("LinkHinhAnh");
        Thongbao thongbao = new Thongbao();

        thongbao.setLoaiThongBao("Thông báo");
        thongbao.setNoiDung("Có sản phẩm cần duyệt");

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("Users");
        Query query = ref.orderByChild("id").equalTo(id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                User user = snapshot.getValue(User.class);

                if (user != null){

                    thongbao.setLinkAnh(user.getImageURL());

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn_DangSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = Utils.showLoader(NhapDuLieuSanPhamActivity.this);

                LayIDadmin();

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
                    String hanSuDung = edt_HanSuDung.getDayOfMonth() + "/" + (edt_HanSuDung.getMonth())
                            +"/"+edt_HanSuDung.getYear();
                    String noiSanXuat = edt_NoiSanXuat.getEditText().getText().toString().trim();
                    String gioiHanViTri = edt_GioiHanViTri.getEditText().getText().toString().trim();
                    String moTaSanPham = edt_MoTaSanPham.getEditText().getText().toString().trim();
                    String gia = edt_Gia.getEditText().getText().toString().trim();
                    String idSanPham = ref.push().getKey();

                    if (TextUtils.isEmpty(String.valueOf(soLuong))){
                        edt_SoLuong.setError("Họ và tên không được để trống !");
                    }
                    if (soLuong < 1){
                        edt_SoLuong.setError("Số lượng phải lớn hơn 1!");
                    }
                    if (TextUtils.isEmpty(gia)){
                        edt_Gia.setError("Giá không được bỏ trống");
                    }
                    if (Double.parseDouble(gia) == 0){
                        edt_Gia.setError("Giá phải lớn hơn 0 !");
                    }
                    if (TextUtils.isEmpty(noiSanXuat)){
                        edt_NoiSanXuat.setError("Nơi sản xuất không được bỏ trống !");
                    }
                    if (TextUtils.isEmpty(gioiHanViTri)){
                        edt_GioiHanViTri.setError("Giới hạn vị trí không được bỏ trống !");
                    }
                    if (TextUtils.isEmpty(moTaSanPham)){
                        edt_MoTaSanPham.setError("Mô tả không được bỏ trống !");
                    }

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

                            for (int i = 0; i < idAdmin.size(); i++){

                                ref = database.getReference("ThongBao");

                                thongbao.setIDNguoiNhan(idAdmin.get(i));
                                thongbao.setIDThongBao(ref.push().getKey());
                                ref.child(thongbao.getIDThongBao()).setValue(thongbao);
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

    private void LayIDadmin() {

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("Users");
        Query query = ref.orderByChild("loai").equalTo(2);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                User user = snapshot.getValue(User.class);

                if ( user != null ){

                    idAdmin.add(user.getId());

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
//    public void chonngay() {
//
//        int selectedYear = 2021;
//        int selectedMonth = 12;
//        int selectedDayOfMonth = 19;
//
//        // Date Select Listener.
//        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int year,
//                                  int monthOfYear, int dayOfMonth) {
//
//                edt_HanSuDung.getEditText().setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//            }
//        };
//
//        // Create DatePickerDialog (Spinner Mode):
//        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
//                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
//                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
//
//// Show
//        datePickerDialog.show();
//    }
}