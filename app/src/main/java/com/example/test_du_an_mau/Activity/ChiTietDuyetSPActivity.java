package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_du_an_mau.Adapter.SlideShowAdapter;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.Domian.Thongbao;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.Domian.Utils;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChiTietDuyetSPActivity extends AppCompatActivity {

    ViewPager2 vp_SildeHinhAnhDuyet;

    SlideShowAdapter slideShowAdapter;

    ImageView img_AnhNguoiDangDuyet, img_BackCTDuyet, img_prevDuyet, img_nextDuyet;

    TextView txt_TenNguoiDungDuyet, txt_LoaiHinhSPDuyet, txt_LoaiSanPhamCTDuyet,
            txt_LoaiCTCTSPDuyet, txt_SoLuongCTDuyet, txt_DonViCTDuyet, txt_HanSuDungCTDuyet,
            txt_NoiSanXuatCTDuyet, txt_GioiHanCTDuyet, txt_MoTaCTDuyet, txt_Duyet, txt_Xoa;

    FirebaseDatabase database;

    List<String> listmaSP, listMaID;

    DatabaseReference ref;

    String linkAnh;

    int currentImage;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_duyet_spactivity);

        vp_SildeHinhAnhDuyet = this.findViewById(R.id.vp_SildeHinhAnhDuyet);

        img_AnhNguoiDangDuyet = this.findViewById(R.id.img_AnhNguoiDangDuyet);
        img_BackCTDuyet = this.findViewById(R.id.img_BackCTDuyet);
        img_prevDuyet = this.findViewById(R.id.img_prevDuyet);
        img_nextDuyet = this.findViewById(R.id.img_nextDuyet);

        txt_TenNguoiDungDuyet = this.findViewById(R.id.txt_TenNguoiDungDuyet);
        txt_LoaiHinhSPDuyet = this.findViewById(R.id.txt_LoaiHinhSPDuyet);
        txt_LoaiSanPhamCTDuyet = this.findViewById(R.id.txt_LoaiSanPhamCTDuyet);
        txt_LoaiCTCTSPDuyet = this.findViewById(R.id.txt_LoaiCTCTSPDuyet);
        txt_SoLuongCTDuyet = this.findViewById(R.id.txt_SoLuongCTDuyet);
        txt_DonViCTDuyet = this.findViewById(R.id.txt_DonViCTDuyet);
        txt_HanSuDungCTDuyet = this.findViewById(R.id.txt_HanSuDungCTDuyet);
        txt_NoiSanXuatCTDuyet = this.findViewById(R.id.txt_NoiSanXuatCTDuyet);
        txt_GioiHanCTDuyet = this.findViewById(R.id.txt_GioiHanCTDuyet);
        txt_MoTaCTDuyet = this.findViewById(R.id.txt_MoTaCTDuyet);
        txt_Duyet = this.findViewById(R.id.txt_Duyet);
        txt_Xoa = this.findViewById(R.id.txt_Xoa);

        listmaSP = new ArrayList<>();
        listMaID = new ArrayList<>();

        if (getIntent().getExtras() != null){

            SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("DuLieuSanPham");

            txt_LoaiHinhSPDuyet.setText(sanPhamDomian.getLoaiHinhSP());
            txt_LoaiSanPhamCTDuyet.setText(sanPhamDomian.getLoaiSP());
            txt_LoaiCTCTSPDuyet.setText(sanPhamDomian.getLoaiChiTietSP());
            txt_SoLuongCTDuyet.setText(String.valueOf(sanPhamDomian.getSoLuong()));
            txt_DonViCTDuyet.setText(sanPhamDomian.getDonVi());
            txt_HanSuDungCTDuyet.setText(sanPhamDomian.getHanSuDung());
            txt_NoiSanXuatCTDuyet.setText(sanPhamDomian.getNoiSanXuat());
            txt_MoTaCTDuyet.setText(sanPhamDomian.getMoTaChiTiet());

            String id = sanPhamDomian.getMaNguoiDung();
            LayTenNguoiDung(id);

            List<String> listHinhAnh = sanPhamDomian.getAlbumAnh();
            slideShowAdapter = new SlideShowAdapter(this, listHinhAnh);
            vp_SildeHinhAnhDuyet.setAdapter(slideShowAdapter);

            vp_SildeHinhAnhDuyet.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

                    currentImage = position;

                    if (currentImage == slideShowAdapter.getItemCount()-1){
                        img_nextDuyet.setEnabled(false);
                        img_prevDuyet.setEnabled(true);
                    } else if (currentImage == 0){
                        img_prevDuyet.setEnabled(false);
                        img_nextDuyet.setEnabled(true);
                    } else {
                        img_nextDuyet.setEnabled(true);
                        img_prevDuyet.setEnabled(true);
                    }

                }
            });

            currentImage = 0;

            //ti???n ???nh
            img_nextDuyet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentImage++;
                    vp_SildeHinhAnhDuyet.setCurrentItem(currentImage);
                }
            });

            //l??i ???nh
            img_prevDuyet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentImage--;
                    vp_SildeHinhAnhDuyet.setCurrentItem(currentImage);
                }
            });

            LayIDNguoiDang(sanPhamDomian);
            Thongbao thongbao = new Thongbao();
            thongbao.setLoaiThongBao("Th??ng b??o");
            thongbao.setNoiDung("Ng?????i d??ng c?? s???n ph???m b???n y??u th??ch ???? ????ng s???n ph???m m???i");
            thongbao.setLinkAnh(linkAnh);

            txt_Duyet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog = Utils.showLoader(ChiTietDuyetSPActivity.this);

                    database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                    DatabaseReference reff = database.getReference("SanPham");

                    reff.child(sanPhamDomian.getMaSP()).setValue(sanPhamDomian).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            ref = database.getReference("DuyetSP");
                            ref.child(sanPhamDomian.getMaSP()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                    database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                                    DatabaseReference reference = database.getReference("ThongBao");

                                        for (int i = 0; i < listMaID.size(); i++){

                                            thongbao.setIDThongBao(reference.push().getKey());
                                            thongbao.setIDNguoiNhan(listMaID.get(i));
                                            reference.child(thongbao.getIDThongBao()).setValue(thongbao).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void unused) {

                                                    if(dialog!=null){
                                                        dialog.dismiss();
                                                    }

                                                }
                                            });

                                        }

                                    if(dialog!=null){
                                        dialog.dismiss();
                                    }

                                    Toast.makeText(ChiTietDuyetSPActivity.this, "???? duy???t !!", Toast.LENGTH_SHORT).show();

                                    startActivity(new Intent(ChiTietDuyetSPActivity.this, DuyetSanPhamActivity.class));
                                    finish();

                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            if(dialog!=null){
                                dialog.dismiss();
                            }
                            Toast.makeText(ChiTietDuyetSPActivity.this, "error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            });

            txt_Xoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dialog = Utils.showLoader(ChiTietDuyetSPActivity.this);

                    ref = database.getReference("DuyetSP");

                    ref.child(sanPhamDomian.getMaSP()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            if(dialog!=null){
                                dialog.dismiss();
                            }
                            Toast.makeText(ChiTietDuyetSPActivity.this, "???? X??a !!", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(ChiTietDuyetSPActivity.this, DuyetSanPhamActivity.class));
                            finish();

                        }
                    });

                }
            });

        }

        img_BackCTDuyet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void LayIDNguoiDang(SanPhamDomian sanPhamDomian) {

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("SanPham");
        Query query = ref.orderByChild("maNguoiDung").equalTo(sanPhamDomian.getMaNguoiDung());
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);

                listmaSP.add(sanPhamDomian.getMaSP());
                ref.child(sanPhamDomian.getMaSP()).child("NYT").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                        String idND = snapshot.getValue(String.class);
                        listMaID.add(idND);

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

    private void LayTenNguoiDung(String id) {

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("Users");
        Query query = ref.orderByChild("id").equalTo(id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null){
                    txt_TenNguoiDungDuyet.setText(user.getUsername());
                    linkAnh = user.getImageURL();
                    Picasso.get().load(user.getImageURL()).placeholder(R.drawable.user).into(img_AnhNguoiDangDuyet);

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