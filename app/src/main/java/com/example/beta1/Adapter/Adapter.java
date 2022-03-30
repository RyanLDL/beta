package com.example.beta1.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.beta1.Data.TiktokBean;
import com.example.beta1.R;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private List<TiktokBean> videoBeans = new ArrayList<>();
    private OnItemClickListenner onItemClickListenner;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        TiktokBean bean=videoBeans.get(position);
        Glide.with(holder.imageView.getContext()).load(bean.coverImgUrl).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                monItemClickListenner.onItemClick(view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return videoBeans.size();
    }

    private OnItemClickListenner monItemClickListenner;
    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.pic);
        }
    }
    public interface OnItemClickListenner{
        void onItemClick(View view,int position);
    }
    public void setOnItemClickListenner(OnItemClickListenner onItemClickListenner){
        monItemClickListenner = onItemClickListenner;
    }
}
