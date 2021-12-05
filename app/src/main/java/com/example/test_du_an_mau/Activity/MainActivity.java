package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.test_du_an_mau.R;
import com.example.test_du_an_mau.fragment.fragment_favorite;
import com.example.test_du_an_mau.fragment.fragment_home;
import com.example.test_du_an_mau.fragment.fragment_them;
import com.example.test_du_an_mau.fragment.fragment_user;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private static final int FRAGMENT_TRANG_CHINH = 0;
    private static final int FRAGMENT_FARORITE = 1;
    private static final int FRAGMENT_USER = 2;
    private static final int FRAGMENT_THEM = 3;

    private int mCurrentFragment = FRAGMENT_TRANG_CHINH;
    Button btn_themSanPham;
    String id;

    FirebaseUser firebaseUser;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = this.findViewById(R.id.btnv_trangchu);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemReselectedListener);
        replaceFragment(new fragment_home());
        bottomNavigationView.getMenu().findItem(R.id.fragment_home).setChecked(true);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        id = mAuth.getUid();

        if (id != null){
            reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());
        }

        btn_themSanPham = this.findViewById(R.id.btn_dangbai);

        btn_themSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (id != null){
                    startActivity(new Intent(MainActivity.this, ChonLoaiHinhSanPhamActivity.class));
                } else {
                    startActivity(new Intent(MainActivity.this, DangNhapActivity.class));
                }

            }
        });

    }

    //Tạo onClick cho item của BottomNavigationView
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemReselectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    int id = item.getItemId();
                    if (id == R.id.fragment_home){
                        if (mCurrentFragment != FRAGMENT_TRANG_CHINH){
                            replaceFragment(new fragment_home());
                            mCurrentFragment = FRAGMENT_TRANG_CHINH;
                        }
                    } else if (id == R.id.fragment_like){
                        if (mCurrentFragment != FRAGMENT_FARORITE){
                            replaceFragment(new fragment_favorite());
                            mCurrentFragment = FRAGMENT_FARORITE;
                        }
                    } else if (id == R.id.fragment_admin){
                        if (mCurrentFragment != FRAGMENT_USER){
                            replaceFragment(new fragment_user());
                            mCurrentFragment = FRAGMENT_USER;
                        }
                    } else if (id == R.id.fragment_sanpham){
                        if (mCurrentFragment != FRAGMENT_THEM){
                            replaceFragment(new fragment_them());
                            mCurrentFragment = FRAGMENT_THEM;
                        }
                    }
                    return true;
                }
            };

    //gắn các fragment vào MainActivity
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fl_fragment_container, fragment);
        transaction.commit();
    }

    //trạng thái của người dùng
    private void status(String status){

        if (id == null){
            return;
        }
        reference = FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", status);

        reference.updateChildren(hashMap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        status("online");
    }

    @Override
    protected void onPause() {
        super.onPause();
        status("offline");
    }

}
