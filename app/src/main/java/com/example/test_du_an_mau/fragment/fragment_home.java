package com.example.test_du_an_mau.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.test_du_an_mau.Activity.ChiTietSanPhamActivity;
import com.example.test_du_an_mau.Activity.DangNhapActivity;
import com.example.test_du_an_mau.Activity.LoaiSanPhamActivity;
import com.example.test_du_an_mau.Activity.NhanTinActivity;
import com.example.test_du_an_mau.Activity.TimKiemActivity;
import com.example.test_du_an_mau.Adapter.LoaiSPAdapter;
import com.example.test_du_an_mau.Adapter.SanPhamMoiAdapter;
import com.example.test_du_an_mau.Domian.Favorite;
import com.example.test_du_an_mau.Domian.LoaiSPDomian;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
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
import java.util.HashMap;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class fragment_home extends Fragment {

    private RecyclerView lspList, rscv_SanPhamMoiNhat;
    private LoaiSPAdapter loaiSPAdapter;
    CircleImageView img_AnhNguoiDungHome;
    DatabaseReference ref, reff;
    LinearLayout lnl_TimKiem;
    List<SanPhamDomian> list_SanPhamMoi;
    private SanPhamMoiAdapter sanPhamAdapter;
    private FirebaseDatabase database;
    private View view;
    private static final int MY_REQUEST_CODE = 10;
    ImageView img_mess;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_home, container, false);

        img_AnhNguoiDungHome = view.findViewById(R.id.img_AnhNguoiDungHome);
        img_mess = view.findViewById(R.id.img_mess);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String id = mAuth.getUid();

        lspList = view.findViewById(R.id.rccv_LoaiSanPham);
        rscv_SanPhamMoiNhat = view.findViewById(R.id.rscv_SanPhamMoiNhat);
        loaiSPAdapter = new LoaiSPAdapter();
        sanPhamAdapter = new SanPhamMoiAdapter();
        rscv_SanPhamMoiNhat.setHasFixedSize(true);
        rscv_SanPhamMoiNhat.setLayoutManager(new GridLayoutManager(getActivity().getApplicationContext(), 2));
        getDuLieuSanPham();
        list_SanPhamMoi = new ArrayList<>();
        sanPhamAdapter.setData(list_SanPhamMoi, new SanPhamMoiAdapter.SanPhamOnClick() {
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
                reff = database.getReference("YeuThich");

                if (currentUser == null){
                    startActivity(new Intent(getActivity(), DangNhapActivity.class));
                }

                if (id == null){
                    return;
                }

                Favorite favorite = new Favorite();

                favorite.setYeuThich(1);
                favorite.setIdSanPham(id.trim());

                reff.child(id).child(sanPhamDomian.getMaSP().trim()).setValue(favorite.toMap()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });

                DatabaseReference reference = database.getReference("SanPham").child(sanPhamDomian.getMaSP()).child("NYT");

                reference.child(id).setValue(id).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void DaThichOnclick(SanPhamDomian sanPhamDomian) {
                database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
                reff = database.getReference("YeuThich");

                if (currentUser == null){
                    startActivity(new Intent(getActivity(), DangNhapActivity.class));
                }

                if (id == null){
                    return;
                }

                reff.child(id).child(sanPhamDomian.getMaSP().trim()).removeValue();

                DatabaseReference reference = database.getReference("SanPham").child(sanPhamDomian.getMaSP()).child("NYT");

                reference.child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(getActivity(), "Đã xóa khỏi yêu thích", Toast.LENGTH_SHORT).show();
                    }
                });

            }

            @Override
            public void KiemTraYeuThich(SanPhamDomian sanPhamDomian) {
            }
        }, mAuth.getUid());
        rscv_SanPhamMoiNhat.setAdapter(sanPhamAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        lspList.setLayoutManager(linearLayoutManager);


        loaiSPAdapter.setData(getListLSP(), new LoaiSPAdapter.LoaiOnClick() {
            @Override
            public void SpOnclick(String loai) {
                Intent intent = new Intent(getActivity(), LoaiSanPhamActivity.class);

                intent.putExtra("LoaiSP", loai);

                startActivityForResult(intent, MY_REQUEST_CODE);
            }
        });
        lspList.setAdapter(loaiSPAdapter);

        lnl_TimKiem = view.findViewById(R.id.lnl_TimKiem);

        //Bấm vào ô tìm kiếm

        lnl_TimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TimKiemActivity.class));
            }
        });
        img_mess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), NhanTinActivity.class);

                intent.putExtra("id", id);

                startActivity(intent);
            }
        });

        TenNguoiDung();

        return view;
    }

    private void getDuLieuSanPham() {

        database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
        ref = database.getReference("SanPham");
        Query query = ref.orderByChild("ngayThem").limitToLast(8);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                SanPhamDomian sanPhamDomian = snapshot.getValue(SanPhamDomian.class);
                if (sanPhamDomian != null){
                    list_SanPhamMoi.add(0, sanPhamDomian);
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

    }

    private List<LoaiSPDomian> getListLSP() {
        List<LoaiSPDomian> list = new ArrayList<>();
        list.add(new LoaiSPDomian(R.drawable.coffee, "Cà phê"));
        list.add(new LoaiSPDomian(R.drawable.pepper, "Tiêu"));
        list.add(new LoaiSPDomian(R.drawable.cashew, "Điều"));
        list.add(new LoaiSPDomian(R.drawable.wheat, "Lúa"));
        list.add(new LoaiSPDomian(R.drawable.meat, "Thịt"));
        list.add(new LoaiSPDomian(R.drawable.cocoa, "Ca cao"));
        list.add(new LoaiSPDomian(R.drawable.whiskey, "Rượu"));
        return list;
    }

    private void TenNguoiDung(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String id = mAuth.getUid();

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
                    Picasso.get().load(user.getImageURL()).placeholder(R.drawable.user).into(img_AnhNguoiDungHome);
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