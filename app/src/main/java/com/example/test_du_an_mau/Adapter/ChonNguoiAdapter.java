package com.example.test_du_an_mau.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.Domian.Thongbao;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChonNguoiAdapter extends RecyclerView.Adapter<ChonNguoiAdapter.ViewHolder> {

    List<User> list;
    ChonNguoiOnClick chonNguoiOnClick;

    public interface ChonNguoiOnClick{
        void ChonNguoi(User user, int i);
    }

    public void setData(List<User> list, ChonNguoiOnClick chonNguoiOnClick){
        this.list = list;
        this.chonNguoiOnClick = chonNguoiOnClick;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chon_nguoi, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        User user = list.get(position);

        if (user != null){

            holder.txt_ChonTen.setText(user.getUsername());

            String linkanh = user.getImageURL();
            Picasso.get().load(linkanh).placeholder(R.drawable.ic_baseline_image_24).into(holder.img_ChonAnh);

            holder.lnl_ChonNguoi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chonNguoiOnClick.ChonNguoi(user, position);
                }
            });

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

        ImageView img_ChonAnh;
        TextView txt_ChonTen;
        ConstraintLayout lnl_ChonNguoi;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_ChonAnh = itemView.findViewById(R.id.img_ChonAnh);
            txt_ChonTen = itemView.findViewById(R.id.txt_ChonTen);
            lnl_ChonNguoi = itemView.findViewById(R.id.lnl_ChonNguoi);
        }
    }
}
