package com.example.test_du_an_mau.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Activity.ChiTietSanPhamActivity;
import com.example.test_du_an_mau.Adapter.SanPhamMoiAdapter;
import com.example.test_du_an_mau.Domian.Favorite;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
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

public class fragment_favorite extends Fragment {

    CircleImageView img_AnhNguoiDungFVR;
    DatabaseReference ref, reff;
    private FirebaseDatabase database;
    private SanPhamMoiAdapter sanPhamAdapter;
    private static final int MY_REQUEST_CODE = 10;
    RecyclerView rscv_SanPhamYeuThich;
    List<SanPhamDomian> list = new ArrayList<>();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    List<String> maSP;
    String ma;
    String id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_favorite, container, false);

        img_AnhNguoiDungFVR = view.findViewById(R.id.img_AnhNguoiDungFVR);
        rscv_SanPhamYeuThich = view.findViewById(R.id.rscv_SanPhamYeuThich);

        id = mAuth.getUid();

        TenNguoiDung();

        maSP = new ArrayList<>();

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("SanPham");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                LayMaSanPham(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sanPhamAdapter = new SanPhamMoiAdapter();
        rscv_SanPhamYeuThich.setHasFixedSize(true);
        rscv_SanPhamYeuThich.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        sanPhamAdapter.setData(list, new SanPhamMoiAdapter.SanPhamOnClick() {
            @Override
            public void SpOnclick(SanPhamDomian sanPhamDomian) {
                Intent intent = new Intent(getActivity(), ChiTietSanPhamActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("DuLieuSanPham",sanPhamDomian);

                intent.putExtras(bundle);

                startActivityForResult(intent, MY_REQUEST_CODE);
            }

            @Override
            public void YeuThichOnclick(SanPhamDomian sanPhamDomian) {
                database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                DatabaseReference reference1 = database.getReference("YeuThich");

                if (id == null){
                    return;
                }

                Favorite favorite = new Favorite();

                favorite.setYeuThich(1);
                favorite.setIdSanPham(id.trim());
                reference1.child(id).child(sanPhamDomian.getMaSP().trim()).child("NYT").setValue(favorite.toMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void DaThichOnclick(SanPhamDomian sanPhamDomian) {
                database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                DatabaseReference databaseReference = database.getReference("YeuThich");

                if (id == null){
                    return;
                }

                Favorite favorite = new Favorite();

                favorite.setYeuThich(2);
                favorite.setIdSanPham(id.trim());

                databaseReference.child(id).child(sanPhamDomian.getMaSP().trim()).removeValue();
            }

            @Override
            public void KiemTraYeuThich(SanPhamDomian sanPhamDomian) {

            }
        }, mAuth.getUid());

        rscv_SanPhamYeuThich.setAdapter(sanPhamAdapter);

        return view;
    }

    private void LayMaSanPham(DataSnapshot snapshot) {

        if (snapshot.exists()){
            for (DataSnapshot sn:snapshot.getChildren()){

                String loai = sn.child("maSP").getValue(String.class);

                maSP.add(loai);
                
            }

            for (int i = 0; i < maSP.size(); i++){

                database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                ref = database.getReference("SanPham");
                String masp = maSP.get(i);
                Query query = ref.child(masp).child("NYT").child(id);
                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()){

                            ma = masp;

                            database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                            reff = database.getReference("SanPham");

                            Query query = reff.orderByChild("maSP").equalTo(ma);
                            query.addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                                    SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);
                                    if (sanPhamDomian != null){
                                        list.add(sanPhamDomian);
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

                        } else return;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                
            }

        } else {
            Log.d("SanPham", "không có dữ liệu!!");
        }

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
                    Picasso.get().load(user.getImageURL()).placeholder(R.drawable.user).into(img_AnhNguoiDungFVR);
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