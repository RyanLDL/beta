package com.example.beta1.Adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
        TiktokBean bean1=videoBeans.get(position+1);
        Glide.with(holder.videoView.getContext()).load(bean.coverImgUrl).centerCrop().into(holder.videoView.posterImageView);
        holder.name.setText(bean.authorName);
        holder.title.setText(bean.title);
//        holder.like.setText(bean.likeCount);
        holder.mPsition = position;
        PreloadManager.getInstance(holder.itemView.getContext()).addPreloadTask(bean.videoDownloadUrl,position);
        PreloadManager.getInstance(holder.itemView.getContext()).addPreloadTask(bean1.videoDownloadUrl,position+1);


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
        private boolean isgood = false;
        private VideoView videoView;
        private TextView title,name,like;
        private ImageButton imageButton;
        int mPsition;
        public Holder(@NonNull View itemView) {
            super(itemView);

            videoView=itemView.findViewById(R.id.videoview);
            title=itemView.findViewById(R.id.content);
            name=itemView.findViewById(R.id.name);
            like = itemView.findViewById(R.id.like);
            imageButton = itemView.findViewById(R.id.imagebtn);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isgood){
                        imageButton.setImageResource(R.drawable.ic_dianzan);
                        Integer num=Integer.parseInt(like.getText().toString());
                        int a = num.intValue();
                        Log.d(TAG, "onClick: "+(a+1));
                        like.setText(String.valueOf(a+1));
                        isgood = true;
                    }else {
                        imageButton.setImageResource(R.drawable.ic_dianzan_1);
                        Integer num = Integer.parseInt(like.getText().toString());
                        int a = num.intValue();
                        like.setText(String.valueOf(a-1));
                        isgood = false;
                    }
                }
            });

        }
    }
}
