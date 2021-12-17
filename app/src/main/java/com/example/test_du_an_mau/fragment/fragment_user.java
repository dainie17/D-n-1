package com.example.test_du_an_mau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Activity.MainActivity;
import com.example.test_du_an_mau.Adapter.ThongBaoAdapter;
import com.example.test_du_an_mau.Domian.Thongbao;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class fragment_user extends Fragment {


    RecyclerView rscv_ThongBao;
    CircleImageView img_AnhNguoiDungTB;
    ThongBaoAdapter adapter;
    List<Thongbao> list;
    DatabaseReference ref;

    private FirebaseDatabase database;

    String id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_user, container, false);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        id = firebaseUser.getUid();

        img_AnhNguoiDungTB = view.findViewById(R.id.img_AnhNguoiDungTB);
        TenNguoiDung();
        rscv_ThongBao = view.findViewById(R.id.rscv_ThongBao);
        rscv_ThongBao.setHasFixedSize(true);
        rscv_ThongBao.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 1));
        adapter = new ThongBaoAdapter();
        list = new ArrayList<>();
        layDuLieuThongBao();
        adapter.setData(list);
        rscv_ThongBao.setAdapter(adapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getActivity().getApplicationContext(), DividerItemDecoration.VERTICAL);
        rscv_ThongBao.addItemDecoration(itemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                Thongbao thongbao = list.get(position);

                database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                ref = database.getReference("ThongBao");
                ref.child(thongbao.getIDThongBao()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });

        itemTouchHelper.attachToRecyclerView(rscv_ThongBao);

        return view;
    }

    private void layDuLieuThongBao() {

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("ThongBao");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                Thongbao thongbao = snapshot.getValue(Thongbao.class);

                if (thongbao != null){

                    String listID = thongbao.getIDNguoiNhan();

                        if (id.equals(listID)){

                            list.add(thongbao);
                            adapter.notifyDataSetChanged();

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
                    Picasso.get().load(user.getImageURL()).placeholder(R.drawable.user).into(img_AnhNguoiDungTB);
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