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
import android.widget.LinearLayout;

import com.example.test_du_an_mau.Activity.ChiTietSanPhamActivity;
import com.example.test_du_an_mau.Activity.TimKiemActivity;
import com.example.test_du_an_mau.Adapter.LoaiSPAdapter;
import com.example.test_du_an_mau.Adapter.SanPhamMoiAdapter;
import com.example.test_du_an_mau.Domian.LoaiSPDomian;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class fragment_home extends Fragment {

    private RecyclerView lspList, rscv_SanPhamMoiNhat;
    private LoaiSPAdapter loaiSPAdapter;
    DatabaseReference ref;
    LinearLayout lnl_TimKiem;
    List<SanPhamDomian> list_SanPhamMoi;
    private SanPhamMoiAdapter sanPhamAdapter;
    private FirebaseDatabase database;
    private View view;
    private static final int MY_REQUEST_CODE = 10;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_home, container, false);

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
        });
        rscv_SanPhamMoiNhat.setAdapter(sanPhamAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        lspList.setLayoutManager(linearLayoutManager);


        loaiSPAdapter.setData(getListLSP());
        lspList.setAdapter(loaiSPAdapter);

        lnl_TimKiem = view.findViewById(R.id.lnl_TimKiem);

        //Bấm vào ô tìm kiếm

        lnl_TimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), TimKiemActivity.class));
            }
        });

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
        list.add(new LoaiSPDomian(R.drawable.ic_baseline_image_24, "cà phê"));
        list.add(new LoaiSPDomian(R.drawable.ic_baseline_image_24, "tiêu"));
        list.add(new LoaiSPDomian(R.drawable.ic_baseline_image_24, "điều"));
        list.add(new LoaiSPDomian(R.drawable.ic_baseline_image_24, "lúa"));
        list.add(new LoaiSPDomian(R.drawable.ic_baseline_image_24, "hoa quả"));
        return list;
    }

}