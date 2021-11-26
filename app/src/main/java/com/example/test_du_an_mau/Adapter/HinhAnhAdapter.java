package com.example.test_du_an_mau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Domian.ChonHinh;
import com.example.test_du_an_mau.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HinhAnhAdapter extends RecyclerView.Adapter<HinhAnhAdapter.MyViewHolder> {

    Context context;
    List<ChonHinh> mList;

    public HinhAnhAdapter(Context context, List<ChonHinh> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HinhAnhAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_hinh_anh, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HinhAnhAdapter.MyViewHolder holder, int position) {

        holder.txt_TenHinhItem.setText(mList.get(position).getLinkHinhAnh());
        Picasso.get().load(mList.get(position).getImage()).placeholder(R.drawable.ic_baseline_image_24).into(holder.img_HinhAnhItem);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_TenHinhItem;
        ImageView img_HinhAnhItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_TenHinhItem = itemView.findViewById(R.id.txt_TenHinhItem);

            img_HinhAnhItem = itemView.findViewById(R.id.img_HinhAnhItem);

        }
    }
}
