package com.example.beta1.tool;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.example.beta1.R;
import com.zhongjh.albumcamerarecorder.album.engine.ImageEngine;

public class Glide4Engine implements ImageEngine {
    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView, Uri uri) {
        Glide.with(context)
                .asBitmap() // some .jpeg files are actually gif
                .load(uri)
                .apply(new RequestOptions()
                        .override(resize, resize)
                        .placeholder(placeholder)
                        .centerCrop())
                .into(imageView);
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView imageView,
                                 Uri uri) {
        Glide.with(context)
                .asBitmap() // some .jpeg files are actually gif
                .load(uri)
                .apply(new RequestOptions()
                        .override(resize, resize)
                        .placeholder(placeholder)
                        .centerCrop())
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(context)
                .load(uri)
                .apply(new RequestOptions()
                        .override(resizeX, resizeY)
                        .priority(Priority.HIGH)
                        .error(R.drawable.ic_failed)
                        .fitCenter())
                .into(imageView);
    }

    @Override
    public void loadUrlImage(Context context, ImageView imageView, String url) {
        Glide.with(context)
                .load(url)
                .apply(new RequestOptions()
                        .priority(Priority.HIGH)
                        .error(R.drawable.ic_failed)
                        .fitCenter())
                .into(imageView);
    }

    @Override
    public void loadUriImage(Context context, ImageView imageView, Uri uri) {
        Glide.with(context)
                .load(uri)
                .apply(new RequestOptions()
                        .priority(Priority.HIGH)
                        .error(R.drawable.ic_failed)
                        .fitCenter())
                .into(imageView);
    }

    @Override
    public void loadDrawableImage(Context context, ImageView imageView, Integer resourceId) {
        Glide.with(context)
                .load(resourceId)
                .apply(new RequestOptions()
                        .priority(Priority.HIGH)
                        .error(R.drawable.ic_failed)
                        .fitCenter())
                .into(imageView);
    }

    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        Glide.with(context)
                .asGif()
                .load(uri)
                .apply(new RequestOptions()
                        .override(resizeX, resizeY)
                        .priority(Priority.HIGH)
                        .fitCenter())
                .into(imageView);
    }

    @Override
    public boolean supportAnimatedGif() {
        return true;
    }

}



