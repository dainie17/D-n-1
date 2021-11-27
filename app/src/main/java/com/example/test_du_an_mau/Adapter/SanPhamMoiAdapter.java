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

public class SanPhamMoiAdapter extends RecyclerView.Adapter<SanPhamMoiAdapter.SanPhamViewHolder> {

    List<SanPhamDomian> sanPhamDomianList;
    private SanPhamOnClick sanPhamOnClick;

    public interface SanPhamOnClick{
        void SpOnclick(SanPhamDomian sanPhamDomian);
    }

    public void setData(List<SanPhamDomian> sanPhamDomianList, SanPhamOnClick sanPhamOnClick) {
        this.sanPhamDomianList = sanPhamDomianList;
        this.sanPhamOnClick = sanPhamOnClick;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SanPhamMoiAdapter.SanPhamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_san_pham_moi, parent, false);
        return new SanPhamViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamMoiAdapter.SanPhamViewHolder holder, int position) {

        SanPhamDomian sanPhamDomian = sanPhamDomianList.get(position);

        if (sanPhamDomian == null){
            return;
        }

        holder.txt_TenLoai.setText(sanPhamDomian.getLoaiSP());
        holder.txt_LoaiChiTiet.setText(sanPhamDomian.getLoaiChiTietSP());
        String soLuong = String.valueOf(sanPhamDomian.getSoLuong());
        holder.txt_SoLuong.setText(soLuong);
        holder.txt_DonVi.setText(sanPhamDomian.getDonVi());

        String linkAnh = sanPhamDomian.getAlbumAnh().get(0);
        Picasso.get().load(linkAnh).placeholder(R.drawable.ic_baseline_image_24).into(holder.img_AnhSanPham1);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPhamOnClick.SpOnclick(sanPhamDomian);
            }
        });

    }

    @Override
    public int getItemCount() {

        if (sanPhamDomianList != null){
            return sanPhamDomianList.size();
        }

        return 0;
    }

    public class SanPhamViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_GiaBanDau, txt_TenLoai, txt_LoaiChiTiet, txt_SoLuong, txt_DonVi, txt_DanhGia;
        private ImageView img_AnhSanPham1, img_YeuThich;
        private ConstraintLayout constraintLayout;

        public SanPhamViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_GiaBanDau = itemView.findViewById(R.id.txt_GiaBanDau);
            txt_TenLoai = itemView.findViewById(R.id.txt_TenLoai);
            txt_LoaiChiTiet = itemView.findViewById(R.id.txt_LoaiChiTiet);
            txt_SoLuong = itemView.findViewById(R.id.txt_SoLuong);
            txt_DonVi = itemView.findViewById(R.id.txt_DonVi);
            txt_DanhGia = itemView.findViewById(R.id.txt_DanhGia);

            img_AnhSanPham1 = itemView.findViewById(R.id.img_AnhSanPham1);
            img_YeuThich = itemView.findViewById(R.id.img_YeuThich);

            constraintLayout = itemView.findViewById(R.id.ItemSanPham);

        }
    }
}
