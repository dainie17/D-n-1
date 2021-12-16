package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test_du_an_mau.Adapter.SanPhamMoiAdapter;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class TrangCaNhanActivity extends AppCompatActivity {

    RecyclerView rscv_SanPhamCN;
    SanPhamMoiAdapter sanPhamAdapter;
    FirebaseDatabase database;
    DatabaseReference ref;
    List<SanPhamDomian> list_SanPhamKhac;
    ImageView img_AnhCn, img_QuayLaiCN;
    TextView txt_TenDungCN, txt_SDTCN, txt_DiaChi, txt_XoaNguoiDung;
    private static final int MY_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_ca_nhan);

        rscv_SanPhamCN = this.findViewById(R.id.rscv_SanPhamCN);
        img_AnhCn = this.findViewById(R.id.img_AnhCn);
        img_QuayLaiCN = this.findViewById(R.id.img_QuayLaiCN);
        txt_TenDungCN = this.findViewById(R.id.txt_TenDungCN);
        txt_SDTCN = this.findViewById(R.id.txt_SDTCN);
        txt_DiaChi = this.findViewById(R.id.txt_DiaChi);
        txt_XoaNguoiDung = this.findViewById(R.id.txt_XoaNguoiDung);

        if (getIntent().getExtras() != null) {

            User taikhoan = (User) getIntent().getExtras().get("DuLieuNguoiDung");

            FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

            database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
            ref = database.getReference("Users");
            Query query = ref.orderByChild("id").equalTo(firebaseUser.getUid());
            query.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    User user = snapshot.getValue(User.class);
                    if (user != null){
                        String linank = user.getImageURL();

                        Picasso.get().load(linank).into(img_AnhCn);

                        txt_TenDungCN.setText(user.getUsername());
                        txt_SDTCN.setText(user.getPhone());
                        txt_DiaChi.setText(user.getDiaChi());
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

            sanPhamAdapter = new SanPhamMoiAdapter();
            rscv_SanPhamCN.setHasFixedSize(true);
            rscv_SanPhamCN.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
            database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
            ref = database.getReference("SanPham");
            Query query1 = ref.orderByChild("maNguoiDung").equalTo(firebaseUser.getUid());
            query1.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);
                    if (sanPhamDomian != null) {
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
                    Intent intent = new Intent(TrangCaNhanActivity.this, ChiTietSanPhamActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("DuLieuSanPham", sanPhamDomian);

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
            }, firebaseUser.getUid());
            rscv_SanPhamCN.setAdapter(sanPhamAdapter);

            txt_XoaNguoiDung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    for (int i = 0; i < list_SanPhamKhac.size(); i++){

                        String masp = list_SanPhamKhac.get(i).getMaSP();

                        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                        DatabaseReference reference = database.getReference("SanPham");
                        reference.child(masp).removeValue();

                    }

                    database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                    ref = database.getReference("Users");
                    ref.child(firebaseUser.getUid()).removeValue();

                    firebaseUser.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Intent intent = new Intent(TrangCaNhanActivity.this, QuanLyTaiKhoanActivity.class);

                            FirebaseAuth mAuth = FirebaseAuth.getInstance();
                            mAuth.signInWithEmailAndPassword(taikhoan.getEmail(), taikhoan.getPass()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){

                                        startActivity(intent);

                                    }else {
                                        Toast.makeText(getApplicationContext(), "Đăng nhập không thành công ", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                        }
                    });

                }
            });

            img_QuayLaiCN.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(TrangCaNhanActivity.this, QuanLyTaiKhoanActivity.class);

                    FirebaseAuth mAuth = FirebaseAuth.getInstance();
                    mAuth.signOut();
                    mAuth.signInWithEmailAndPassword(taikhoan.getEmail(), taikhoan.getPass()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){

                                startActivity(intent);

                            }else {
                                Toast.makeText(getApplicationContext(), "Đăng nhập không thành công ", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
                }
            });

        }

    }

}