package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.OpenableColumns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_du_an_mau.Adapter.HinhAnhAdapter;
import com.example.test_du_an_mau.Adapter.SanPhamCaNhanAdapter;
import com.example.test_du_an_mau.Adapter.SlideShowAdapter;
import com.example.test_du_an_mau.Domian.ChonHinh;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.Domian.Utils;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class QuanLySanPhamActivity extends AppCompatActivity {

    TextView ten_nguoi_dung, txt_SoDienThoaiSP;

    ImageView img_Quaylai, img_AnhNguoiDungSP;

    RecyclerView rscv_QuanLySanPham;

    SanPhamCaNhanAdapter sanPhamCaNhanAdapter;

    private FirebaseDatabase database;

    DatabaseReference ref;

    List<SanPhamDomian> list;

    RecyclerView rscv_HienAnhSua;

    SlideShowAdapter slideShowAdapter;

    String LoaiHinhSua;
    String LoaiSua;
    String LoaiChiTietSua;

    private static final int MY_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_san_pham);

        ten_nguoi_dung = findViewById(R.id.txt_ten_nguoi_dung);
        img_Quaylai = this.findViewById(R.id.img_QuayLai);
        rscv_QuanLySanPham = this.findViewById(R.id.rscv_QuanLySanPham);
        img_AnhNguoiDungSP = this.findViewById(R.id.img_AnhNguoiDungSP);
        txt_SoDienThoaiSP = this.findViewById(R.id.txt_SoDienThoaiSP);

        TenNguoiDung();

        img_Quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sanPhamCaNhanAdapter = new SanPhamCaNhanAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rscv_QuanLySanPham.setLayoutManager(linearLayoutManager);
        getLisviewDatabasefirebase();
        list = new ArrayList<>();
        sanPhamCaNhanAdapter.setData(list, new SanPhamCaNhanAdapter.upDateClick() {
            @Override
            public void onClickUpDate(SanPhamDomian sanPhamDomian) {
                openDialogUpdate(sanPhamDomian);
            }

            @Override
            public void onClickDelete(SanPhamDomian sanPhamDomian) {
                onClickDeleteData(sanPhamDomian);
            }
        });

        rscv_QuanLySanPham.setAdapter(sanPhamCaNhanAdapter);


    }

    private void openDialogUpdate(SanPhamDomian sanPhamDomian) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.sua_san_pham);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);


        Button btn_Regup, btn_Relayup;
        ImageView img_ChonHinh;
        AutoCompleteTextView edt_SuaLoaiHinh, edt_SuaLoai, edt_SuaChiTiet, edt_DonVi;
        TextInputEditText edt_SoLuongSua, edt_GiaSua, edt_HanSua, edt_NoiSanXuatSua, edt_ViTriSua, edt_MoTaSanPhamSua;

        btn_Relayup = dialog.findViewById(R.id.btn_Relayup);
        btn_Regup = dialog.findViewById(R.id.btn_Regup);
        rscv_HienAnhSua = dialog.findViewById(R.id.rscv_HienAnhSua);
        edt_SuaLoaiHinh = dialog.findViewById(R.id.edt_SuaLoaiHinh);
        edt_SuaLoai = dialog.findViewById(R.id.edt_SuaLoai);
        edt_SuaChiTiet = dialog.findViewById(R.id.edt_SuaChiTiet);
        edt_DonVi = dialog.findViewById(R.id.edt_DonVi);
        edt_SoLuongSua = dialog.findViewById(R.id.edt_SoLuongSua);
        edt_GiaSua = dialog.findViewById(R.id.edt_GiaSua);
        edt_HanSua = dialog.findViewById(R.id.edt_HanSua);
        edt_NoiSanXuatSua = dialog.findViewById(R.id.edt_NoiSanXuatSua);
        edt_ViTriSua = dialog.findViewById(R.id.edt_ViTriSua);
        edt_MoTaSanPhamSua = dialog.findViewById(R.id.edt_MoTaSanPhamSua);

        rscv_HienAnhSua.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getParent(), RecyclerView.HORIZONTAL, false);
        rscv_HienAnhSua.setLayoutManager(linearLayoutManager);
        List<String> listHinhAnh = sanPhamDomian.getAlbumAnh();

        slideShowAdapter = new SlideShowAdapter(this, listHinhAnh);
        rscv_HienAnhSua.setAdapter(slideShowAdapter);

        edt_SuaLoaiHinh.setText(sanPhamDomian.getLoaiHinhSP());
        ArrayList<String> LoaiHinh = new ArrayList<>();
        LoaiHinh.add("N??ng nghi???p c?? b???n");
        LoaiHinh.add("Cac San Pham Phat Sinh");
        LoaiHinh.add("C??c S???n Ph???m ???? Ch?? Bi???n");
        ArrayAdapter adapterLoaiHinh = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiHinh);
        edt_SuaLoaiHinh.setAdapter(adapterLoaiHinh);

        String loaihinh = edt_SuaLoaiHinh.getText().toString();

        ArrayList<String> LoaiCoBan = new ArrayList<>();
        LoaiCoBan.add("C?? ph??");
        LoaiCoBan.add("Ti??u");
        LoaiCoBan.add("H???t ??i???u");
        LoaiCoBan.add("Ca Cao");
        LoaiCoBan.add("MacCa");
        ArrayAdapter adapterLoaiCoban = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiCoBan);

        ArrayList<String> LoaiPhatSinh = new ArrayList<>();
        LoaiPhatSinh.add("Th???t");
        LoaiPhatSinh.add("B??nh m??");
        LoaiPhatSinh.add("B??");
        LoaiPhatSinh.add("D???u");
        ArrayAdapter adapterLoaiPhatSinh = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiPhatSinh);

        ArrayList<String> LoaiDaCheBien = new ArrayList<>();
        LoaiDaCheBien.add("B??nh");
        LoaiDaCheBien.add("N?????c ng???t");
        LoaiDaCheBien.add("X??c x??ch");
        LoaiDaCheBien.add("R?????u");
        ArrayAdapter adapterLoaiCheBien = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiDaCheBien);

        edt_SuaLoai.setText(sanPhamDomian.getLoaiSP());

        if (loaihinh.equals("N??ng nghi???p c?? b???n")){

            edt_SuaLoai.setAdapter(adapterLoaiCoban);

        } else if (loaihinh.equals("Cac San Pham Phat Sinh")){

            edt_SuaLoai.setAdapter(adapterLoaiPhatSinh);

        } else if (loaihinh.equals("C??c S???n Ph???m ???? Ch?? Bi???n")){

            edt_SuaLoai.setAdapter(adapterLoaiCheBien);

        }

        edt_SuaLoaiHinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LoaiHinhSua = parent.getItemAtPosition(position).toString();

                if (LoaiHinhSua.equals("N??ng nghi???p c?? b???n")){

                    edt_SuaLoai.setAdapter(adapterLoaiCoban);

                } else if (LoaiHinhSua.equals("Cac San Pham Phat Sinh")){

                    edt_SuaLoai.setAdapter(adapterLoaiPhatSinh);

                } else if (LoaiHinhSua.equals("C??c S???n Ph???m ???? Ch?? Bi???n")){

                    edt_SuaLoai.setAdapter(adapterLoaiCheBien);

                }

            }
        });

        String loai = edt_SuaLoai.getText().toString();

        ArrayList<String> LoaiChiTietCaPhe = new ArrayList<>();
        LoaiChiTietCaPhe.add("ROBUSTA");
        LoaiChiTietCaPhe.add("ARABICA");
        LoaiChiTietCaPhe.add("CHERRY");
        LoaiChiTietCaPhe.add("CULI");
        LoaiChiTietCaPhe.add("MOKA");
        LoaiChiTietCaPhe.add("Kh??ng X??c ?????nh");
        ArrayAdapter adapterLoaiChiTietCaPhe = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietCaPhe);

        ArrayList<String> LoaiChiTietCaCao = new ArrayList<>();
        LoaiChiTietCaCao.add("Criollo");
        LoaiChiTietCaCao.add("Trinitario");
        LoaiChiTietCaCao.add("Forastero");
        LoaiChiTietCaCao.add("Kh??ng X??c ?????nh");
        ArrayAdapter adapterLoaiChiTietCaCao = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietCaCao);

        ArrayList<String> LoaiChiTietDieu = new ArrayList<>();
        LoaiChiTietDieu.add("H???t ??i???u Nh??n Tr???ng");
        LoaiChiTietDieu.add("H???t ??i???u Nh??n V???");
        LoaiChiTietDieu.add("H???t ??i???u Nh??n V??ng");
        LoaiChiTietDieu.add("H???t ??i???u Nh??n B??? N??m, Teo, X??u");
        LoaiChiTietDieu.add("Kh??ng X??c ?????nh");
        ArrayAdapter adapterLoaiChiTietDieu = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietDieu);

        ArrayList<String> LoaiChiTietMC = new ArrayList<>();
        LoaiChiTietMC.add("MacCao ??c");
        LoaiChiTietMC.add("MacCao Trung Qu???c");
        LoaiChiTietMC.add("MacCao Nam Phi");
        LoaiChiTietMC.add("MacCao Vi???t Nam");
        LoaiChiTietMC.add("Kh??ng X??c ?????nh");
        ArrayAdapter adapterLoaiChiTietMC = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietMC);

        ArrayList<String> LoaiChiTietTieu = new ArrayList<>();
        LoaiChiTietTieu.add("Ti??u ??en");
        LoaiChiTietTieu.add("Ti??u X???");
        LoaiChiTietTieu.add("Ti??u ?????");
        LoaiChiTietTieu.add("Ti??u L??p");
        ArrayAdapter adapterLoaiChiTietTieu = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietTieu);

        ArrayList<String> LoaiChiTietBanh = new ArrayList<>();
        LoaiChiTietBanh.add("B??nh M??");
        LoaiChiTietBanh.add("B??nh K???o");
        LoaiChiTietBanh.add("Kh??ng X??c ?????nh");
        ArrayAdapter adapterLoaiChiTietBanh = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietBanh);

        ArrayList<String> LoaiChiTietNuoc = new ArrayList<>();
        LoaiChiTietNuoc.add("N?????c L???c");
        LoaiChiTietNuoc.add("N?????c Ng???t");
        LoaiChiTietNuoc.add("C?? Ph??");
        LoaiChiTietNuoc.add("Ca Cao");
        ArrayAdapter adapterLoaiChiTietNuoc = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietNuoc);

        ArrayList<String> LoaiChiTietRuou = new ArrayList<>();
        LoaiChiTietRuou.add("R?????u g???o M???u S??n");
        LoaiChiTietRuou.add("R?????u G?? ??en");
        LoaiChiTietRuou.add("R?????u N???p");
        LoaiChiTietRuou.add("R?????u G???o");
        ArrayAdapter adapterLoaiChiTietRuou = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietRuou);

        ArrayList<String> LoaiChiTietXucXich = new ArrayList<>();
        LoaiChiTietXucXich.add("X??c X??ch Heo");
        LoaiChiTietXucXich.add("X??c X??ch ?????c");
        LoaiChiTietXucXich.add("X??c X??ch ??n Li???n");
        ArrayAdapter adapterLoaiChiTietXucXich = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietXucXich);

        ArrayList<String> LoaiChiTietDau = new ArrayList<>();
        LoaiChiTietDau.add("D???u ?? Liu");
        LoaiChiTietDau.add("D???u B??");
        LoaiChiTietDau.add("D???u M??");
        LoaiChiTietDau.add("D???u ?????u N??nh");
        ArrayAdapter adapterLoaiChiTietDau = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietDau);

        ArrayList<String> LoaiChiTietBo = new ArrayList<>();
        LoaiChiTietBo.add("B?? Nh???t");
        LoaiChiTietBo.add("B?? M???n");
        LoaiChiTietBo.add("B?? ?????ng V???t");
        LoaiChiTietBo.add("B?? Th???c V???t");
        ArrayAdapter adapterLoaiChiTietBo = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietBo);

        ArrayList<String> LoaiChiTietBanhmi = new ArrayList<>();
        LoaiChiTietBanhmi.add("B??nh m?? Ph??p");
        LoaiChiTietBanhmi.add("B??nh m?? n?????ng Kaya");
        LoaiChiTietBanhmi.add("B??nh m?? Mitrailette");
        LoaiChiTietBanhmi.add("B??nh m?? Vi???t Nam");
        ArrayAdapter adapterLoaiChiTietBanhmi = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietBanhmi);

        ArrayList<String> LoaiChiTietThit = new ArrayList<>();
        LoaiChiTietThit.add("Th???t Heo");
        LoaiChiTietThit.add("Th???t B??");
        LoaiChiTietThit.add("Th???t C??");
        LoaiChiTietThit.add("Th???t G??");
        ArrayAdapter adapterLoaiChiTietThit = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, LoaiChiTietThit);

        edt_SuaChiTiet.setText(sanPhamDomian.getLoaiChiTietSP());

        if (loai.equals("C?? ph??")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietCaPhe);

        } else if (loai.equals("Ti??u")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietTieu);

        } else if (loai.equals("H???t ??i???u")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietDieu);

        } else if (loai.equals("Ca Cao")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietCaCao);

        } else if (loai.equals("MacCa")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietMC);

        } else if (loai.equals("Th???t")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietThit);

        } else if (loai.equals("B??nh m??")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietBanhmi);

        } else if (loai.equals("B??")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietBo);

        } else if (loai.equals("D???u")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietDau);

        } else if (loai.equals("B??nh")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietBanh);

        } else if (loai.equals("N?????c ng???t")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietNuoc);

        } else if (loai.equals("X??c x??ch")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietXucXich);

        } else if (loai.equals("R?????u")){

            edt_SuaChiTiet.setAdapter(adapterLoaiChiTietRuou);

        }

        edt_SuaLoai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LoaiSua = parent.getItemAtPosition(position).toString();

                if (LoaiSua.equals("C?? ph??")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietCaPhe);

                } else if (LoaiSua.equals("Ti??u")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietTieu);

                } else if (LoaiSua.equals("H???t ??i???u")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietDieu);

                } else if (LoaiSua.equals("Ca Cao")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietCaCao);

                } else if (LoaiSua.equals("MacCa")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietMC);

                } else if (LoaiSua.equals("Th???t")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietThit);

                } else if (LoaiSua.equals("B??nh m??")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietBanhmi);

                } else if (LoaiSua.equals("B??")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietBo);

                } else if (LoaiSua.equals("D???u")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietDau);

                } else if (LoaiSua.equals("B??nh")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietBanh);

                } else if (LoaiSua.equals("N?????c ng???t")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietNuoc);

                } else if (LoaiSua.equals("X??c x??ch")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietXucXich);

                } else if (LoaiSua.equals("R?????u")){

                    edt_SuaChiTiet.setAdapter(adapterLoaiChiTietRuou);

                }

            }
        });

        edt_SuaChiTiet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                LoaiChiTietSua = parent.getItemAtPosition(position).toString();

            }
        });

        edt_DonVi.setText(sanPhamDomian.getDonVi());

        ArrayList<String> DonVi = new ArrayList<>();
        DonVi.add("kg");
        DonVi.add("t???n");
        DonVi.add("t???");
        DonVi.add("bao");
        DonVi.add("con");
        DonVi.add("c??y");
        ArrayAdapter adapterDonVi = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, DonVi);
        edt_DonVi.setAdapter(adapterDonVi);

        edt_SoLuongSua.setText(String.valueOf(sanPhamDomian.getSoLuong()));
        edt_GiaSua.setText(sanPhamDomian.getGiaBan());
        edt_HanSua.setText(sanPhamDomian.getHanSuDung());
        edt_NoiSanXuatSua.setText(sanPhamDomian.getNoiSanXuat());
        edt_ViTriSua.setText(sanPhamDomian.getGioiHanViTri());
        edt_MoTaSanPhamSua.setText(sanPhamDomian.getMoTaChiTiet());

        btn_Regup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (LoaiHinhSua != null){
                    sanPhamDomian.setLoaiHinhSP(LoaiHinhSua);
                } else {
                    sanPhamDomian.setLoaiHinhSP(edt_SuaLoaiHinh.getText().toString());
                }

                if (LoaiSua != null){
                    sanPhamDomian.setLoaiSP(LoaiSua);
                } else {
                    sanPhamDomian.setLoaiSP(edt_SuaLoai.getText().toString());
                }

                if (LoaiChiTietSua != null){
                    sanPhamDomian.setLoaiChiTietSP(LoaiChiTietSua);
                } else {
                    sanPhamDomian.setLoaiChiTietSP(edt_SuaChiTiet.getText().toString());
                }

                sanPhamDomian.setSoLuong(Integer.parseInt(edt_SoLuongSua.getText().toString()));
                sanPhamDomian.setGiaBan(edt_GiaSua.getText().toString());
                sanPhamDomian.setDonVi(edt_DonVi.getText().toString());
                sanPhamDomian.setHanSuDung(edt_HanSua.getText().toString());
                sanPhamDomian.setNoiSanXuat(edt_NoiSanXuatSua.getText().toString());
                sanPhamDomian.setGioiHanViTri(edt_ViTriSua.getText().toString());
                sanPhamDomian.setMoTaChiTiet(edt_MoTaSanPhamSua.getText().toString());

                database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                ref = database.getReference("SanPham");
                ref.child(sanPhamDomian.getMaSP()).updateChildren(sanPhamDomian.toMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        dialog.dismiss();

                    }
                });
            }
        });

        img_ChonHinh = dialog.findViewById(R.id.img_ChonHinh);

        img_ChonHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), CapNhatHinhActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DuLieuSanPham",sanPhamDomian);

                intent.putExtras(bundle);

                startActivityForResult(intent, MY_REQUEST_CODE);

            }
        });

        btn_Relayup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void onClickDeleteData(SanPhamDomian sanPhamDomian) {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(this);
        aBuilder.setMessage("B???n c?? ch???c ch???n x??a");
        aBuilder.setPositiveButton("C??", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                ref = database.getReference("SanPham");
                ref.child(sanPhamDomian.getMaSP()).removeValue(new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                            }
                        },2000);
                    }
                });
            }
        });
        aBuilder.setNegativeButton("Kh??ng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        aBuilder.show();
    }

    private void getLisviewDatabasefirebase() {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String id = firebaseAuth.getUid();

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("SanPham");
        Query query = ref.orderByChild("maNguoiDung").equalTo(id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);
                if (sanPhamDomian != null) {
                    list.add(sanPhamDomian);
                    sanPhamCaNhanAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(@NonNull   DataSnapshot snapshot, @Nullable   String previousChildName) {
                SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);
                if (sanPhamDomian == null || list == null || list.isEmpty()) {
                    return;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (sanPhamDomian.getMaSP() == list.get(i).getMaSP()) {
                        list.set(i, sanPhamDomian);
                        break;
                    }
                }
                sanPhamCaNhanAdapter.notifyDataSetChanged();
            }



            @Override
            public void onChildRemoved(@NonNull  DataSnapshot snapshot) {
                SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);
                if (sanPhamDomian == null || list == null || list.isEmpty()) {
                    return;
                }
                for (int i = 0; i < list.size(); i++) {
                    if (sanPhamDomian.getMaSP() == list.get(i).getMaSP()) {
                        list.remove(list.get(i));
                        break;
                    }
                }
                sanPhamCaNhanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull   DataSnapshot snapshot, @Nullable   String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void TenNguoiDung(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getUid();

        if (id == null){
            return;
        }

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("Users");
        Query query = ref.orderByChild("id").equalTo(id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null){
                    ten_nguoi_dung.setText(user.getUsername());
                    txt_SoDienThoaiSP.setText(user.getPhone());
                    Picasso.get().load(user.getImageURL()).placeholder(R.drawable.user).into(img_AnhNguoiDungSP);
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

}