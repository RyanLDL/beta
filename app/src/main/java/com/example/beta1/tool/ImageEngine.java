package com.example.beta1.tool;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;

public class ImageEngine implements com.zhongjh.progresslibrary.engine.ImageEngine {
    @Override
    public void loadGifImage(@NonNull Context context, int i, int i1, @NonNull ImageView imageView, @NonNull Uri uri) {
        Glide.with(context)
                .asGif()
                .load(uri)
                .apply(new RequestOptions()
                .override(i,i1)
                .priority(Priority.HIGH)
                .fitCenter())
                .into(imageView);
    }

    @Override
    public void loadGifThumbnail(@NonNull Context context, int i, @Nullable Drawable drawable, @NonNull ImageView imageView, @NonNull Uri uri) {
        Glide.with(context)
                .asBitmap() // some .jpeg files are actually gif
                .load(uri)
                .apply(new RequestOptions()
                        .override(i,i)
                        .placeholder(drawable)
                        .centerCrop())
                .into(imageView);
    }

    @Override
    public void loadImage(@NonNull Context context, int i, int i1, @NonNull ImageView imageView, @NonNull Uri uri) {
        Glide.with(context)
                .asGif()
                .load(uri)
                .apply(new RequestOptions()
                .override(i,i1)
                .priority(Priority.HIGH)
                .fitCenter())
                .into(imageView);
    }

    @Override
    public void loadThumbnail(@NonNull Context context, int i, @NonNull Drawable drawable, @NonNull ImageView imageView, @NonNull Uri uri) {
        Glide.with(context)
                .load(uri)
                .apply(new RequestOptions()
                .override(i,i)
                .placeholder(drawable)
                .centerCrop())
                .into(imageView);
    }

    @Override
    public void loadUrlThumbnail(@NonNull Context context, int i, @NonNull Drawable drawable, @NonNull ImageView imageView, @NonNull String s) {
        Glide.with(context)
                .load(s)
                .apply(new RequestOptions()
                .override(i,i)
                .placeholder(drawable)
                .centerCrop())
                .into(imageView);
    }

    @Override
    public boolean supportAnimatedGif() {
        return true;
    }
}
