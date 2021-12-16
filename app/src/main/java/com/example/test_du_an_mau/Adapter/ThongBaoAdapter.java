package com.example.test_du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Domian.Thongbao;
import com.example.test_du_an_mau.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.ViewHolder> {

    List<Thongbao> list;

    public void setData(List<Thongbao> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_thong_bao, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Thongbao thongbao = list.get(position);

        if (thongbao != null){

            holder.txt_LoaiTBNhan.setText(thongbao.getLoaiThongBao());
            holder.txt_NoiDungTBNhan.setText(thongbao.getNoiDung());

            String linkanh = thongbao.getLinkAnh();
            Picasso.get().load(linkanh).into(holder.img_AnhNguoiTB);

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

        ImageView img_AnhNguoiTB;
        TextView txt_LoaiTBNhan, txt_NoiDungTBNhan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_AnhNguoiTB = itemView.findViewById(R.id.img_AnhNguoiTB);
            txt_LoaiTBNhan = itemView.findViewById(R.id.txt_LoaiTBNhan);
            txt_NoiDungTBNhan = itemView.findViewById(R.id.txt_NoiDungTBNhan);

        }
    }
}
