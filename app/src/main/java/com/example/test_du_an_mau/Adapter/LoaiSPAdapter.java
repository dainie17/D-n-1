package com.example.test_du_an_mau.Adapter;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Domian.LoaiSPDomian;
import com.example.test_du_an_mau.Activity.MainActivity;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;

import java.util.List;

public class LoaiSPAdapter extends RecyclerView.Adapter<LoaiSPAdapter.lspViewHolder>{

    private List<LoaiSPDomian> loaiSPDomians;

    private LoaiSPAdapter.LoaiOnClick loaiOnClick;

    public interface LoaiOnClick{
        void SpOnclick(String loai);
    }

    public LoaiSPAdapter(MainActivity mainActivity) {
    }

    public LoaiSPAdapter() {

    }

    public void setData (List<LoaiSPDomian> list, LoaiOnClick loaiOnClick){
        this.loaiSPDomians = list;
        this.loaiOnClick = loaiOnClick;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public lspViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loai_sp_item, parent, false);
        return new lspViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull lspViewHolder holder, int position) {
        LoaiSPDomian loaiSPDomiann = loaiSPDomians.get(position);
        if (loaiSPDomiann == null){
            return;
        }
        holder.imgLSP.setImageResource(loaiSPDomiann.getResourceId());
        holder.name.setText(loaiSPDomiann.getTitle());
        holder.lnl_LoaiSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loaiOnClick.SpOnclick(loaiSPDomiann.getTitle());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (loaiSPDomians != null){
            return loaiSPDomians.size();
        }
        return 0;
    }

    public class lspViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgLSP;
        private TextView name;
        LinearLayout lnl_LoaiSP;

        public lspViewHolder(@NonNull View itemView) {

            super(itemView);

            imgLSP = itemView.findViewById(R.id.HinhSP);
            name = itemView.findViewById(R.id.TenLSP);
            lnl_LoaiSP = itemView.findViewById(R.id.lnl_LoaiSP);
        }
    }

}
