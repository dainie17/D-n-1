package com.example.test_du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Domian.PhanHoi;
import com.example.test_du_an_mau.Domian.Thongbao;
import com.example.test_du_an_mau.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PhanHoiAdapter extends RecyclerView.Adapter<PhanHoiAdapter.ViewHolder> {

    List<PhanHoi> list;

    public void setData(List<PhanHoi> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phan_hoi, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        PhanHoi phanHoi = list.get(position);

        if (phanHoi != null){

            holder.txt_TenPhanHoi.setText(phanHoi.getTenNguoiGui());
            holder.txt_NoiDungPhanHoi.setText(phanHoi.getNoidung());

            String linkanh = phanHoi.getLinkHinh();
            Picasso.get().load(linkanh).into(holder.circleImageView);

        }

    }

    @Override
    public int getItemCount() {

        if (list != null){
            return list.size();
        }

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView circleImageView;
        TextView txt_TenPhanHoi, txt_NoiDungPhanHoi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            circleImageView = itemView.findViewById(R.id.circleImageView);
            txt_TenPhanHoi = itemView.findViewById(R.id.txt_TenPhanHoi);
            txt_NoiDungPhanHoi = itemView.findViewById(R.id.txt_NoiDungPhanHoi);

        }
    }
}
