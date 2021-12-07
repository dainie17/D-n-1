package com.example.test_du_an_mau.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.Domian.Comment;
import com.example.test_du_an_mau.Domian.User;
import com.example.test_du_an_mau.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Comment> list;

    public void setData(List<Comment> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment comment = list.get(position);
        if (comment == null){
            return;
        }

        String idNguoiDung = comment.getUid();

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebaseDatabase.getReference("Users");

        Query query = ref.orderByChild("id").equalTo(idNguoiDung);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                User user = snapshot.getValue(User.class);
                if (user != null){
                    holder.txt_TenNguoiDung.setText(user.getUsername());
                    String linkAnh = user.getImageURL();
                    if (linkAnh == null){
                        return;
                    } else if (linkAnh.equals("default")){
                        return;
                    }
                    Picasso.get().load(linkAnh).placeholder(R.drawable.ic_baseline_image_24).into(holder.img_HinhNguoiDung);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        holder.txt_NoiDung.setText(comment.getContent());

    }

    @Override
    public int getItemCount() {

        if (list != null){
            return list.size();
        }

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView img_HinhNguoiDung;
        TextView txt_TenNguoiDung, txt_NoiDung;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_HinhNguoiDung = itemView.findViewById(R.id.img_HinhNguoiDung);

            txt_TenNguoiDung = itemView.findViewById(R.id.txt_TenNguoiDungCM);
            txt_NoiDung = itemView.findViewById(R.id.txt_CommentItem);

        }
    }
}
