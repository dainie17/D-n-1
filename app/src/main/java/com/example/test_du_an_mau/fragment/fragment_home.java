package com.example.test_du_an_mau.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test_du_an_mau.Adapter.LoaiSPAdapter;
import com.example.test_du_an_mau.Domian.LoaiSPDomian;
import com.example.test_du_an_mau.R;

import java.util.ArrayList;
import java.util.List;

public class fragment_home extends Fragment {

    private RecyclerView lspList;
    private LoaiSPAdapter loaiSPAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_home, container, false);

        lspList = view.findViewById(R.id.rccv_LoaiSanPham);
        loaiSPAdapter = new LoaiSPAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        lspList.setLayoutManager(linearLayoutManager);


        loaiSPAdapter.setData(getListLSP());
        lspList.setAdapter(loaiSPAdapter);

        return view;
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