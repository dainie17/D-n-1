package com.example.test_du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TaiKhoanAdapter extends RecyclerView.Adapter<TaiKhoanAdapter.ViewHolder>{

    List<User> userList;
    NguoiDungOnClick nguoiDungOnClick;

    public interface NguoiDungOnClick{

        void NDOnClick(User user);

    }

    public void setData(List<User> userList, NguoiDungOnClick nguoiDungOnClick){
        this.userList = userList;
        this.nguoiDungOnClick = nguoiDungOnClick;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_nguoi_dung, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        User user = userList.get(position);

        if (user != null){

            String linkAnh = user.getImageURL();

            Picasso.get().load(linkAnh).into(holder.img_AnhListDung);

            holder.txt_TenDungQL.setText(user.getUsername());
            holder.txt_EmailDung.setText(user.getEmail());

            holder.ctl_NguoiDung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    nguoiDungOnClick.NDOnClick(user);
                }
            });

        }

    }

    @Override
    public int getItemCount() {

        if (userList != null){
            return userList.size();
        }

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img_AnhListDung;
        TextView txt_EmailDung, txt_TenDungQL;
        ConstraintLayout ctl_NguoiDung;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_AnhListDung = itemView.findViewById(R.id.img_AnhListDung);

            txt_EmailDung = itemView.findViewById(R.id.txt_EmailDung);
            txt_TenDungQL = itemView.findViewById(R.id.txt_TenDungQL);

            ctl_NguoiDung = itemView.findViewById(R.id.ctl_NguoiDung);

        }
    }
}
