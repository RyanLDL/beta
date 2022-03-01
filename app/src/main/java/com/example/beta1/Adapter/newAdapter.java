package com.example.beta1.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.beta1.Data.TiktokBean;
import com.example.beta1.R;
import com.example.beta1.cache.PreloadManager;
import com.example.beta1.view.VideoView;

import java.util.List;

public class newAdapter extends RecyclerView.Adapter<newAdapter.Holder> {

    private static final String TAG = "TAG";
    private List<TiktokBean> videoBeans;

    public newAdapter(List<TiktokBean> videoBeans) {
        this.videoBeans = videoBeans;
    }

    @NonNull
    @Override
    public newAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull newAdapter.Holder holder, @SuppressLint("RecyclerView") int position) {

        Log.d(TAG, "onBindViewHolder: ");

        TiktokBean bean=videoBeans.get(position);
        Glide.with(holder.videoView.getContext()).load(bean.coverImgUrl).into(holder.videoView.posterImageView);
        holder.mPsition = position;
        PreloadManager.getInstance(holder.itemView.getContext()).addPreloadTask(bean.videoDownloadUrl,position);


    }

    @Override
    public void onViewDetachedFromWindow(@NonNull Holder holder) {
        super.onViewDetachedFromWindow(holder);
        TiktokBean bean = videoBeans.get(holder.mPsition);
        PreloadManager.getInstance(holder.itemView.getContext()).removePreloadTask(bean.videoDownloadUrl);
    }

    @Override
    public int getItemCount() {
        return videoBeans.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        private VideoView videoView;
        private TextView title,like,name;
        int mPsition;
        public Holder(@NonNull View itemView) {
            super(itemView);
            videoView=itemView.findViewById(R.id.videoview);
            title=itemView.findViewById(R.id.content);
            name=itemView.findViewById(R.id.name);

            like=itemView.findViewById(R.id.like);
        }
    }
}
