package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.test_du_an_mau.Adapter.CommentAdapter;
import com.example.test_du_an_mau.Adapter.SanPhamMoiAdapter;
import com.example.test_du_an_mau.Adapter.SlideShowAdapter;
import com.example.test_du_an_mau.Domian.Comment;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    private static final int MY_CALLPHONE_PERMISSION_CODE = 100;

    ViewPager2 vp_SildeHinhAnh;

    SlideShowAdapter slideShowAdapter;

    ImageView img_prev, img_next, img_BackCT, img_comment;

    TextView txt_LoaiHinhSPCT, txt_LoaiSanPhamCT, txt_LoaiCTCTSP, txt_SoLuongCT, txt_DonViCT,
            txt_HanSuDungCT, txt_NoiSanXuatCT, txt_GioiHanCT, txt_MoTaCT, txt_TenNguoiDungCT,
            txt_NhanTinVNB, txt_Goi;

    EditText edt_coment;

    RecyclerView rscv_CacSanPhamKhac, rscv_Comment;

    DatabaseReference ref;

    FirebaseAuth firebaseAuth;

    String key;

    List<SanPhamDomian> list_SanPhamKhac;

    List<Comment> listComment;

    private SanPhamMoiAdapter sanPhamAdapter;

    private CommentAdapter commentAdapter;

    private FirebaseDatabase database;

    private static final int MY_REQUEST_CODE = 10;

    int currentImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);

        vp_SildeHinhAnh = this.findViewById(R.id.vp_SildeHinhAnh);
        img_prev = this.findViewById(R.id.img_prev);
        img_next = this.findViewById(R.id.img_next);
        img_BackCT = this.findViewById(R.id.img_BackCT);

        txt_LoaiHinhSPCT = this.findViewById(R.id.txt_LoaiHinhSPCT);
        txt_LoaiSanPhamCT = this.findViewById(R.id.txt_LoaiSanPhamCT);
        txt_LoaiCTCTSP = this.findViewById(R.id.txt_LoaiCTCTSP);
        txt_SoLuongCT = this.findViewById(R.id.txt_SoLuongCT);
        txt_DonViCT = this.findViewById(R.id.txt_DonViCT);
        txt_HanSuDungCT = this.findViewById(R.id.txt_HanSuDungCT);
        txt_NoiSanXuatCT = this.findViewById(R.id.txt_NoiSanXuatCT);
        txt_GioiHanCT = this.findViewById(R.id.txt_GioiHanCT);
        txt_MoTaCT = this.findViewById(R.id.txt_MoTaCT);
        txt_TenNguoiDungCT = this.findViewById(R.id.txt_TenNguoiDungCT);
        txt_NhanTinVNB = this.findViewById(R.id.txt_NhanTinVNB);
        txt_Goi = this.findViewById(R.id.txt_Goi);
        rscv_CacSanPhamKhac = this.findViewById(R.id.rscv_CacSanPhamKhac);
        rscv_Comment = this.findViewById(R.id.rscv_Binhluan);
        img_comment = this.findViewById(R.id.img_comment);
        edt_coment = this.findViewById(R.id.edt_comment);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String idmen = auth.getUid();

        if (getIntent().getExtras() != null){

            SanPhamDomian sanPhamDomian = (SanPhamDomian) getIntent().getExtras().get("DuLieuSanPham");

            String loaiHinhSanPham = sanPhamDomian.getLoaiHinhSP();
            txt_LoaiHinhSPCT.setText(loaiHinhSanPham);

            String tenLoai = sanPhamDomian.getLoaiSP();
            txt_LoaiSanPhamCT.setText(tenLoai);

            String loaiCTSP = sanPhamDomian.getLoaiChiTietSP();
            txt_LoaiCTCTSP.setText(loaiCTSP);

            String soLuong = String.valueOf(sanPhamDomian.getSoLuong());
            txt_SoLuongCT.setText(soLuong);

            String donvi = sanPhamDomian.getDonVi();
            txt_DonViCT.setText(donvi);

            String hansudung = sanPhamDomian.getHanSuDung();
            txt_HanSuDungCT.setText(hansudung);

            String noisanxuat = sanPhamDomian.getNoiSanXuat();
            txt_NoiSanXuatCT.setText(noisanxuat);

            String gioihan = sanPhamDomian.getGioiHanViTri();
            txt_GioiHanCT.setText(gioihan);

            String mota = sanPhamDomian.getMoTaChiTiet();
            txt_MoTaCT.setText(mota);

            String id = sanPhamDomian.getMaNguoiDung();

            sanPhamAdapter = new SanPhamMoiAdapter();
            rscv_CacSanPhamKhac.setHasFixedSize(true);
            rscv_CacSanPhamKhac.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
            ref = database.getReference("SanPham");
            Query query = ref.orderByChild("maNguoiDung").equalTo(id);
            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);
                    if (sanPhamDomian != null){
                        list_SanPhamKhac.add(sanPhamDomian);
                        sanPhamAdapter.notifyDataSetChanged();
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
            list_SanPhamKhac = new ArrayList<>();
            sanPhamAdapter.setData(list_SanPhamKhac, new SanPhamMoiAdapter.SanPhamOnClick() {
                @Override
                public void SpOnclick(SanPhamDomian sanPhamDomian) {
                    Intent intent = new Intent(ChiTietSanPhamActivity.this, ChiTietSanPhamActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("DuLieuSanPham",sanPhamDomian);

                    intent.putExtras(bundle);

                    startActivityForResult(intent, MY_REQUEST_CODE);
                }

                @Override
                public void YeuThichOnclick(SanPhamDomian sanPhamDomian) {

                }

                @Override
                public void DaThichOnclick(SanPhamDomian sanPhamDomian) {

                }

                @Override
                public void KiemTraYeuThich(SanPhamDomian sanPhamDomian) {

                }
            }, idmen);
            rscv_CacSanPhamKhac.setAdapter(sanPhamAdapter);

            List<String> listHinhAnh = sanPhamDomian.getAlbumAnh();

            slideShowAdapter = new SlideShowAdapter(this, listHinhAnh);

            vp_SildeHinhAnh.setAdapter(slideShowAdapter);


            vp_SildeHinhAnh.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);

                    currentImage = position;

                    if (currentImage == slideShowAdapter.getItemCount()-1){
                        img_next.setEnabled(false);
                        img_prev.setEnabled(true);
                    } else if (currentImage == 0){
                        img_prev.setEnabled(false);
                        img_next.setEnabled(true);
                    } else {
                        img_next.setEnabled(true);
                        img_prev.setEnabled(true);
                    }

                }
            });

            currentImage = 0;

            //tiến ảnh
            img_next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentImage++;
                    vp_SildeHinhAnh.setCurrentItem(currentImage);
                }
            });

            //lùi ảnh
            img_prev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentImage--;
                    vp_SildeHinhAnh.setCurrentItem(currentImage);
                }
            });

            txt_NhanTinVNB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    String idUser = auth.getUid();
                    if (idUser == null){
                        startActivity(new Intent(ChiTietSanPhamActivity.this, DangNhapActivity.class));
                    }
                    Intent intent = new Intent(ChiTietSanPhamActivity.this, MessageActivity.class);
                    intent.putExtra("userid", sanPhamDomian.getMaNguoiDung());
                    startActivity(intent);
                }
            });

            txt_Goi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String dial = "tel:" + sanPhamDomian.getSoDienThoai();

                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, MY_CALLPHONE_PERMISSION_CODE);
                        } else
                        {
                            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(dial));
                            v.getContext().startActivity(callIntent);
                        }
                    } else
                    {
                        Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse(dial));
                        v.getContext().startActivity(callIntent);
                    }

                }
            });

            img_comment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    img_comment.setVisibility(View.INVISIBLE);
                    FirebaseAuth firebaseUser = FirebaseAuth.getInstance();
                    ref = database.getReference("Comment");
                    String content = edt_coment.getText().toString();
                    String uid = firebaseUser.getUid();
                    Comment cmt = new Comment(content, uid, sanPhamDomian.getMaSP());

                    ref.child(cmt.getIdsp()).setValue(cmt).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            showMessage("Comment thành công: ");
                            edt_coment.setText("");
                            img_comment.setVisibility(View.VISIBLE);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            showMessage("Comment thất bại: " + e.getMessage());
                        }
                    });
                }

                private void showMessage(String message) {
                    Toast.makeText(ChiTietSanPhamActivity.this,message, Toast.LENGTH_SHORT).show();
                }
            });

            commentAdapter = new CommentAdapter();
            rscv_Comment.setHasFixedSize(true);
            rscv_Comment.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
            listComment = new ArrayList<>();
            DatabaseReference reff = database.getReference("Comment");
            reff.child(sanPhamDomian.getMaSP()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Comment comment = snapshot.getValue(Comment.class);
                    if (comment == null){
                        return;
                    }

                    listComment.add(comment);
                    commentAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            commentAdapter.setData(listComment);

            rscv_Comment.setAdapter(commentAdapter);

        }

        img_BackCT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}