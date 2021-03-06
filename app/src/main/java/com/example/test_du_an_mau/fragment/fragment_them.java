package com.example.test_du_an_mau.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test_du_an_mau.Activity.DangKyActivity;
import com.example.test_du_an_mau.Activity.DangKyAdminActivity;
import com.example.test_du_an_mau.Activity.DangNhapActivity;
import com.example.test_du_an_mau.Activity.DanhSachPhanHoiActivity;
import com.example.test_du_an_mau.Activity.DuyetSanPhamActivity;
import com.example.test_du_an_mau.Activity.GuiThongBaoActivity;
import com.example.test_du_an_mau.Activity.NhanTinActivity;
import com.example.test_du_an_mau.Activity.PhanHoiActivity;
import com.example.test_du_an_mau.Activity.QuanLySanPhamActivity;
import com.example.test_du_an_mau.Activity.QuanLyTaiKhoanActivity;
import com.example.test_du_an_mau.Activity.ThietLapActivity;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.example.test_du_an_mau.Activity.UserAcivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class fragment_them extends Fragment {

    TextView txt_SanPhamCuaToi, txt_Them_trangCaNhan, txt_ThietLap,txt_ThemAdmin,
             txt_Them_Ten, txt_TinNhan, txt_DuyetSanPham, txt_KTTaiKhoan, txt_GuiThongBao,
            txt_DangNhap, txt_DangKy, txt_PhanHoi, txt_DanhSachPhanHoi;
    LinearLayout lnl_QuyenAdmin, lnl_ThietLap, lnl_DangNhap;
    CircleImageView img_AnhNguoiDung;
    String id;
    DatabaseReference ref;
    private FirebaseDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_them, container, false);

        txt_SanPhamCuaToi = view.findViewById(R.id.txt_SanPhamCuaToi);
        txt_Them_trangCaNhan = view.findViewById(R.id.txt_Them_trangcanhan);
        txt_Them_Ten = view.findViewById(R.id.txt_Them_Ten);
        txt_TinNhan = view.findViewById(R.id.txt_TinNhan);
        txt_ThietLap = view.findViewById(R.id.txt_thietlap);
        txt_ThemAdmin = view.findViewById(R.id.txt_ThemAdmin);
        txt_DuyetSanPham = view.findViewById(R.id.txt_DuyetSanPham);
        txt_KTTaiKhoan = view.findViewById(R.id.txt_KTTaiKhoan);
        txt_GuiThongBao = view.findViewById(R.id.txt_GuiThongBao);
        txt_DangNhap = view.findViewById(R.id.txt_DangNhap);
        txt_DangKy = view.findViewById(R.id.txt_DangKy);
        txt_PhanHoi = view.findViewById(R.id.txt_PhanHoi);
        txt_DanhSachPhanHoi = view.findViewById(R.id.txt_DanhSachPhanHoi);
        img_AnhNguoiDung = view.findViewById(R.id.img_AnhNguoiDung);
        lnl_QuyenAdmin = view.findViewById(R.id.lnl_QuyenAdmin);
        lnl_ThietLap = view.findViewById(R.id.lnl_ThietLap);
        lnl_DangNhap = view.findViewById(R.id.lnl_DangNhap);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null){
            id = firebaseUser.getUid();
            lnl_ThietLap.setVisibility(View.VISIBLE);
            lnl_DangNhap.setVisibility(View.INVISIBLE);

        } else {

            lnl_ThietLap.setVisibility(View.INVISIBLE);
            lnl_DangNhap.setVisibility(View.VISIBLE);
        }

        txt_DangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DangKyActivity.class));
            }
        });

        txt_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getActivity(), DangNhapActivity.class));

            }
        });

        txt_DanhSachPhanHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DanhSachPhanHoiActivity.class));
            }
        });

        txt_PhanHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id==null){
                    startActivity(new Intent(getActivity(), DangNhapActivity.class));
                } else {

                    startActivity(new Intent(getActivity(), PhanHoiActivity.class));

                }

            }
        });

        OnclickQuanLySanPham();
        OnclickTrangCaNhan();
        TenNguoiDung();
        OnclickTinNhan();
        OnclickThietLap();
        OnClickThemAdmin();
        OnClickDuyetSanPham();
        OnClickQuanLyTaiKhoan();
        OnClickGuiThongBao();

        return view;
    }

    private void OnClickGuiThongBao() {

        txt_GuiThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), GuiThongBaoActivity.class));
            }
        });

    }

    private void OnClickQuanLyTaiKhoan() {

        txt_KTTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), QuanLyTaiKhoanActivity.class));
            }
        });

    }

    private void OnClickDuyetSanPham() {

        txt_DuyetSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DuyetSanPhamActivity.class));
            }
        });

    }

    private void OnClickThemAdmin() {

        txt_ThemAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DangKyAdminActivity.class));
            }
        });

    }

    private void OnclickThietLap(){
        txt_ThietLap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ThietLapActivity.class));
            }
        });
    }
    private void OnclickTinNhan() {

        txt_TinNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id==null){
                    startActivity(new Intent(getActivity(), DangNhapActivity.class));
                } else {

                    Intent intent = new Intent(getActivity(), NhanTinActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);

                }

            }
        });

    }

    private void OnclickTrangCaNhan() {
        txt_Them_trangCaNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(), UserAcivity.class));

            }
        });
    }

    private void OnclickQuanLySanPham() {
        txt_SanPhamCuaToi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), QuanLySanPhamActivity.class));
            }
        });
    }
    private void TenNguoiDung(){

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
                    txt_Them_Ten.setText(user.getUsername());
                    Picasso.get().load(user.getImageURL()).placeholder(R.drawable.user).into(img_AnhNguoiDung);
                    int idnd = user.getLoai();

                    if (idnd == 2){
                        lnl_QuyenAdmin.setVisibility(View.VISIBLE);
                    }else {
                        lnl_QuyenAdmin.setVisibility(View.INVISIBLE);
                    }

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

