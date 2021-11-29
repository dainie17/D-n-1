package com.example.test_du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SanPhamCaNhanAdapter extends RecyclerView.Adapter<SanPhamCaNhanAdapter.SanPhamCuaToiViewHolder> {

    private List<SanPhamDomian> phamDomianList;
    private  upDateClick mupDateClick;

    public interface upDateClick{
        void onClickUpDate(SanPhamDomian sanPhamDomian);
        void onClickDelete(SanPhamDomian sanPhamDomian);
    }

    public void setData(List<SanPhamDomian> list, upDateClick mupDateClick){
        this.phamDomianList = list;
        this.mupDateClick = mupDateClick;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SanPhamCaNhanAdapter.SanPhamCuaToiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quan_ly_san_pham, parent, false);
        return new SanPhamCuaToiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamCaNhanAdapter.SanPhamCuaToiViewHolder holder, int position) {
        SanPhamDomian sanPhamDomian = phamDomianList.get(position);
        if (sanPhamDomian == null){
            return;
        }

        holder.txt_LoaiSP.setText(sanPhamDomian.getLoaiSP());
        holder.txt_LoaiCTSP.setText(sanPhamDomian.getLoaiChiTietSP());

        String linkAnh = sanPhamDomian.getAlbumAnh().get(0);
        Picasso.get().load(linkAnh).placeholder(R.drawable.ic_baseline_image_24).into(holder.img_QuanLyHinhAnh);

        holder.img_UpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mupDateClick.onClickUpDate(sanPhamDomian);
            }
        });

        holder.img_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mupDateClick.onClickDelete(sanPhamDomian);
            }
        });

    }

    @Override
    public int getItemCount() {

        if (phamDomianList != null){
            return phamDomianList.size();
        }

        return 0;
    }

    public class SanPhamCuaToiViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_LoaiSP, txt_LoaiCTSP;

        private ImageView img_QuanLyHinhAnh, img_UpDate, img_Delete;

        public SanPhamCuaToiViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_LoaiSP = itemView.findViewById(R.id.txt_QuanLyLoai);
            txt_LoaiCTSP = itemView.findViewById(R.id.txt_QuanLyLoaiCT);

            img_QuanLyHinhAnh = itemView.findViewById(R.id.img_QuanLyHinhAnhCN);
            img_UpDate = itemView.findViewById(R.id.img_UpDate);
            img_Delete = itemView.findViewById(R.id.img_Delete);

        }
    }
}
