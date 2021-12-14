package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test_du_an_mau.Domian.Chat;
import com.example.test_du_an_mau.Domian.OnItemClick;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.Domian.Utils;
import com.example.test_du_an_mau.R;
import com.example.test_du_an_mau.fragment.ChatsFragment;
import com.example.test_du_an_mau.fragment.UsersFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NhanTinActivity extends AppCompatActivity {

    CircleImageView img_AnhNguoiDungTN;
    TextView username;
    ProgressDialog dialog;
    ImageView back_mess;

    String id;
    DatabaseReference reference;
    OnItemClick onItemClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_tin);

        img_AnhNguoiDungTN = findViewById(R.id.img_AnhNguoiDungTN);
        final TabLayout tbl_TinNhan = findViewById(R.id.tbl_TinNhan);
        final ViewPager vp_TinNhan = findViewById(R.id.vp_TinNhan);
        back_mess = findViewById(R.id.back_mess);



        back_mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        img_AnhNguoiDungTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NhanTinActivity.this, UserAcivity.class));
            }
        });

        username = findViewById(R.id.txt_TenNguoiDungTN);

        id = getIntent().getStringExtra("id");
        reference = FirebaseDatabase.getInstance().getReference("Users").child(id);

        //Lấy dữ liệu người dùng

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                username.setText(user.getUsername());
                if (user.getImageURL().equals("default")){
                    img_AnhNguoiDungTN.setImageResource(R.drawable.ic_launcher_background);
                } else {
                    //change this
                    Picasso.get().load(user.getImageURL()).placeholder(R.drawable.ic_baseline_image_24).into(img_AnhNguoiDungTN);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        dialog = Utils.showLoader(NhanTinActivity.this);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
                int unread = 0;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Chat chat = snapshot.getValue(Chat.class);
                    if (chat.getReceiver().equals(id) && !chat.isIsseen()){
                        unread++;
                    }
                }

                if (unread == 0){
                    viewPagerAdapter.addFragment(ChatsFragment.newInstance(onItemClick), "Chats");
                } else {
                    viewPagerAdapter.addFragment(ChatsFragment.newInstance(onItemClick), "("+unread+") Chats");
                }

                viewPagerAdapter.addFragment(UsersFragment.newInstance(onItemClick), "Users");

                vp_TinNhan.setAdapter(viewPagerAdapter);

                tbl_TinNhan.setupWithViewPager(vp_TinNhan);
                if(dialog!=null){
                    dialog.dismiss();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPagerAdapter(FragmentManager fm){
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            titles.add(title);
        }

        // Ctrl + O

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

}