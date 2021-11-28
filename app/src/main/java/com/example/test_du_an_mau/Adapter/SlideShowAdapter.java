package com.example.test_du_an_mau.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_du_an_mau.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SlideShowAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> list;

    public SlideShowAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_slide, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder)holder).binData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView img_HinhAnhSlide;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            img_HinhAnhSlide = itemView.findViewById(R.id.img_HinhAnhSlide);

        }

        void binData(String linkAnh){
            Picasso.get().load(linkAnh).placeholder(R.drawable.ic_baseline_image_24).into(img_HinhAnhSlide);
        }

    }


}
