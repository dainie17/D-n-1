package com.example.test_du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DuyetSanPhamAdapter extends RecyclerView.Adapter<DuyetSanPhamAdapter.DuyetViewHolder> {

    List<SanPhamDomian> sanPhamDomianList;
    private SanPhamDuyetOnClick sanPhamDuyetOnClick;

    public interface SanPhamDuyetOnClick{
        void SpOnclick(SanPhamDomian sanPhamDomian);
    }

    public void setData(List<SanPhamDomian> sanPhamDomianList, SanPhamDuyetOnClick sanPhamDuyetOnClick) {
        this.sanPhamDomianList = sanPhamDomianList;
        this.sanPhamDuyetOnClick = sanPhamDuyetOnClick;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DuyetSanPhamAdapter.DuyetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_duyet_sanpham, parent, false);
        return new DuyetViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DuyetSanPhamAdapter.DuyetViewHolder holder, int position) {

        SanPhamDomian sanPhamDomian = sanPhamDomianList.get(position);

        if (sanPhamDomian == null){
            return;
        }

        holder.ctl_DuyetSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPhamDuyetOnClick.SpOnclick(sanPhamDomian);
            }
        });

        String linkAnh = sanPhamDomian.getAlbumAnh().get(0);
        Picasso.get().load(linkAnh).placeholder(R.drawable.ic_baseline_image_24).into(holder.img_AnhSanPhamDuyet);

        holder.txt_LoaiSanPhamDuyet.setText(sanPhamDomian.getLoaiSP());
        holder.txt_ChiTietLoaiSanPhamDuyet.setText(sanPhamDomian.getLoaiChiTietSP());

    }

    @Override
    public int getItemCount() {

        if (sanPhamDomianList != null){
            return sanPhamDomianList.size();
        }

        return 0;
    }

    public class DuyetViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout ctl_DuyetSanPham;
        ImageView img_AnhSanPhamDuyet;
        TextView txt_LoaiSanPhamDuyet, txt_ChiTietLoaiSanPhamDuyet;

        public DuyetViewHolder(@NonNull View itemView) {
            super(itemView);

            ctl_DuyetSanPham = itemView.findViewById(R.id.ctl_DuyetSanPham);
            img_AnhSanPhamDuyet = itemView.findViewById(R.id.img_AnhSanPhamDuyet);
            txt_LoaiSanPhamDuyet = itemView.findViewById(R.id.txt_LoaiSanPhamDuyet);
            txt_ChiTietLoaiSanPhamDuyet = itemView.findViewById(R.id.txt_ChiTietLoaiSanPhamDuyet);

        }
    }
}
