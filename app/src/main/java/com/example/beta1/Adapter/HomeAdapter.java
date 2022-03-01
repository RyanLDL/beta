package com.example.beta1.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.beta1.Data.VideoData;
import com.example.beta1.R;
import com.example.beta1.view.VideoView;

import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private VideoData videoData=new VideoData();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        JZDataSource jzDataSource=new JZDataSource(videoData.getVideoUrl().get(position));
        holder.videoView.setUp(jzDataSource, Jzvd.SCREEN_NORMAL);
        jzDataSource.looping=true;//循环
        holder.content.setText(videoData.getVideoContent().get(position));
        holder.name.setText(videoData.getVideoUserName().get(position));
//        Glide.with(holder.videoView.getContext()).load(videoData.getVideoCover().get(position
//        )).into(holder.videoView.posterImageView);
    }

    @Override
    public int getItemCount() {
        return videoData.getVideoUrl().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name,content,good;
        private VideoView videoView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            content=itemView.findViewById(R.id.content);
            videoView=itemView.findViewById(R.id.videoview);
        }
    }
}
