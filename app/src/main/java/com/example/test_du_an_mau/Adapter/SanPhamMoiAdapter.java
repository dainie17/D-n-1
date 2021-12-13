package com.example.test_du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Domian.Favorite;
import com.example.test_du_an_mau.Domian.SanPhamDomian;
import com.example.test_du_an_mau.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.List;

public class SanPhamMoiAdapter extends RecyclerView.Adapter<SanPhamMoiAdapter.SanPhamViewHolder> {

    List<SanPhamDomian> sanPhamDomianList;
    private SanPhamOnClick sanPhamOnClick;
    String Thich;
    int yeu;

    public interface SanPhamOnClick{
        void SpOnclick(SanPhamDomian sanPhamDomian);
        void YeuThichOnclick(SanPhamDomian sanPhamDomian);
        void DaThichOnclick(SanPhamDomian sanPhamDomian);
        void KiemTraYeuThich(SanPhamDomian sanPhamDomian);
    }

    public void setData(List<SanPhamDomian> sanPhamDomianList, SanPhamOnClick sanPhamOnClick, String thich) {
        this.sanPhamDomianList = sanPhamDomianList;
        this.sanPhamOnClick = sanPhamOnClick;
        this.Thich = thich;
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

        sanPhamOnClick.KiemTraYeuThich(sanPhamDomian);

            if (Thich != null){

            FirebaseDatabase database = FirebaseDatabase.getInstance("https://asigment-a306b-default-rtdb.asia-southeast1.firebasedatabase.app/");
            DatabaseReference ref = database.getReference("YeuThich").child(Thich);
            ref.child(sanPhamDomian.getMaSP()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!snapshot.exists()){
                        return;
                    } else {
                        Favorite favorite = snapshot.getValue(Favorite.class);

                        if (favorite == null){
                            holder.img_DaThich.setVisibility(View.INVISIBLE);
                            holder.img_YeuThich.setVisibility(View.INVISIBLE);
                        } else {

                            yeu = favorite.getYeuThich();

                            if (yeu == 1){
                                holder.img_DaThich.setVisibility(View.VISIBLE);
                                holder.img_YeuThich.setVisibility(View.INVISIBLE);
                            } else {
                                holder.img_DaThich.setVisibility(View.INVISIBLE);
                                holder.img_YeuThich.setVisibility(View.VISIBLE);
                            }

                        }

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            }

        holder.txt_TenLoai.setText(sanPhamDomian.getLoaiSP());
        holder.txt_LoaiChiTiet.setText(sanPhamDomian.getLoaiChiTietSP());
        String soLuong = String.valueOf(sanPhamDomian.getSoLuong());
        holder.txt_SoLuong.setText(soLuong);
        holder.txt_DonVi.setText(sanPhamDomian.getDonVi());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        double giaban = Double.parseDouble(sanPhamDomian.getGiaBan());
        holder.txt_GiaBanDau.setText(decimalFormat.format(giaban)+" Đ");
        String linkAnh = sanPhamDomian.getAlbumAnh().get(0);
        Picasso.get().load(linkAnh).placeholder(R.drawable.ic_baseline_image_24).into(holder.img_AnhSanPham1);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPhamOnClick.SpOnclick(sanPhamDomian);
            }
        });

        holder.img_YeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPhamOnClick.YeuThichOnclick(sanPhamDomian);
                holder.img_YeuThich.setVisibility(View.INVISIBLE);
                holder.img_DaThich.setVisibility(View.VISIBLE);
            }
        });

        holder.img_DaThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sanPhamOnClick.DaThichOnclick(sanPhamDomian);
                holder.img_YeuThich.setVisibility(View.VISIBLE);
                holder.img_DaThich.setVisibility(View.INVISIBLE);
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
        private ImageView img_AnhSanPham1, img_YeuThich, img_DaThich;
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
            img_DaThich = itemView.findViewById(R.id.img_DaThich);

            constraintLayout = itemView.findViewById(R.id.ItemSanPham);

        }
    }
}
