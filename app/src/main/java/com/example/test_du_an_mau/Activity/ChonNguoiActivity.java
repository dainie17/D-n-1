package com.example.test_du_an_mau.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.test_du_an_mau.Adapter.ChonNguoiAdapter;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class ChonNguoiActivity extends AppCompatActivity {

    RecyclerView rscv_ChonNguoi;
    Button btn_Xong;
    ChonNguoiAdapter chonNguoiAdapter;
    List<User> list;
    List<String> listID;
    FirebaseDatabase database;
    DatabaseReference ref;
    private static final int MY_REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chon_nguoi);

        rscv_ChonNguoi = this.findViewById(R.id.rscv_ChonNguoi);
        btn_Xong = this.findViewById(R.id.btn_Xong);
        listID = new ArrayList<>();
        rscv_ChonNguoi.setHasFixedSize(true);
        rscv_ChonNguoi.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
        chonNguoiAdapter = new ChonNguoiAdapter();
        list = new ArrayList<>();
        layDuLieuUser();

        chonNguoiAdapter.setData(list, new ChonNguoiAdapter.ChonNguoiOnClick() {
            @Override
            public void ChonNguoi(User user, int i) {

            listID.add(user.getId());
            list.remove(i);
            chonNguoiAdapter.notifyDataSetChanged();

            }
        });

        rscv_ChonNguoi.setAdapter(chonNguoiAdapter);

        btn_Xong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ChonNguoiActivity.this, GuiThongBaoActivity.class);

               intent.putStringArrayListExtra("listId", (ArrayList<String>) listID);

                startActivityForResult(intent, MY_REQUEST_CODE);
                finish();

            }
        });

    }

    private void layDuLieuUser() {

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("Users");
        Query query = ref.orderByChild("loai").equalTo(1);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null){
                    list.add(user);
                    chonNguoiAdapter.notifyDataSetChanged();
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