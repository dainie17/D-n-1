package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.test_du_an_mau.Adapter.TaiKhoanAdapter;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnCompleteListener;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuanLyTaiKhoanActivity extends AppCompatActivity {

    RecyclerView rscv_NguoiDung;
    TaiKhoanAdapter taiKhoanAdapter;
    List<User> userList;
    FirebaseDatabase database;
    DatabaseReference ref;
    ImageView img_back_qlnd;
    private static final int MY_REQUEST_CODE = 10;
    User TaiKhoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tai_khoan);

        rscv_NguoiDung = this.findViewById(R.id.rscv_NguoiDung);
        img_back_qlnd = this.findViewById(R.id.img_back_qlnd);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        TaiKhoanAdmin(firebaseUser.getUid());

        rscv_NguoiDung.setHasFixedSize(true);
        rscv_NguoiDung.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        LayDuLieuNguoiDung();
        userList = new ArrayList<>();
        taiKhoanAdapter = new TaiKhoanAdapter();
        taiKhoanAdapter.setData(userList, new TaiKhoanAdapter.NguoiDungOnClick() {
            @Override
            public void NDOnClick(User user) {

                Intent intent = new Intent(QuanLyTaiKhoanActivity.this, TrangCaNhanActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DuLieuNguoiDung", TaiKhoan);

                intent.putExtras(bundle);

                FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                mAuth.signInWithEmailAndPassword(user.getEmail(), user.getPass()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){

                            startActivityForResult(intent, MY_REQUEST_CODE);

                        }else {
                            Toast.makeText(getApplicationContext(), "Đăng nhập không thành công ", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }
        });
        rscv_NguoiDung.setAdapter(taiKhoanAdapter);

        img_back_qlnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(QuanLyTaiKhoanActivity.this, MainActivity.class));
            }
        });

    }

    private void TaiKhoanAdmin(String id){
        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("Users");
        Query query = ref.orderByChild("id").equalTo(id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null){

                    TaiKhoan = user;

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

    private void LayDuLieuNguoiDung() {

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("Users");
        Query query = ref.orderByChild("loai").equalTo(1);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null){
                    userList.add(user);
                    taiKhoanAdapter.notifyDataSetChanged();
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