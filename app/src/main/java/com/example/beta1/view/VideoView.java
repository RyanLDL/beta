package com.example.beta1.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.beta1.R;

import cn.jzvd.JzvdStd;

public class VideoView extends JzvdStd {
    public VideoView(Context context) {
        super(context);
    }

    public VideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public void init(Context context) {
        super.init(context);
        bottomContainer.setVisibility(GONE);//底部内容区  GONG：隐藏控件，界面不保留该控件空间 invisible：隐藏控件，界面保留该控件空间 visible：可见
        topContainer.setVisibility(GONE);//顶部内容区（返回键等）
        bottomProgressBar.setVisibility(GONE);//底部进度条
        posterImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);//封面
    }


    //修改ui
    @Override
    public void changeUiToNormal() {
        super.changeUiToNormal();
        bottomContainer.setVisibility(GONE);
        topContainer.setVisibility(GONE);
    }

    //设置所有控件可见性
    @Override
    public void setAllControlsVisiblity(int topCon, int bottomCon, int startBtn, int loadingPro,
                                        int posterImg, int bottomPro, int retryLayout) {
        topContainer.setVisibility(INVISIBLE);
        bottomContainer.setVisibility(INVISIBLE);
        startButton.setVisibility(startBtn);
        loadingProgressBar.setVisibility(loadingPro);//加载进度条
        posterImageView.setVisibility(posterImg);
        bottomProgressBar.setVisibility(GONE);
        mRetryLayout.setVisibility(retryLayout);//包含暂定开始按钮的在内的一小块布局
    }

    //关闭控制视图
    @Override
    public void dissmissControlView() {
        if (state != STATE_NORMAL           //如果状态不是正常状态和错误状态和自动完成状态
                && state != STATE_ERROR
                && state != STATE_AUTO_COMPLETE) {
            post(() -> {
                bottomContainer.setVisibility(View.INVISIBLE);
                topContainer.setVisibility(View.INVISIBLE);
                startButton.setVisibility(View.INVISIBLE);
                if (clarityPopWindow != null) { //弹窗
                    clarityPopWindow.dismiss();
                }
                if (screen != SCREEN_TINY) {
                    bottomProgressBar.setVisibility(View.GONE);
                }
            });
        }
    }

    //点击ui界面
    @Override
    public void onClickUiToggle() {
        super.onClickUiToggle();
        Log.i(TAG, "click blank");
        startButton.performClick();//执行点击
        bottomContainer.setVisibility(GONE);
        topContainer.setVisibility(GONE);
    }

    //开始暂停图片的切换
    public void updateStartImage() {
        if (state == STATE_PLAYING) {
            startButton.setVisibility(VISIBLE);
            startButton.setImageResource(R.drawable.video_stop);
            replayTextView.setVisibility(GONE);
        } else if (state == STATE_ERROR) {
            startButton.setVisibility(INVISIBLE);
            replayTextView.setVisibility(GONE);
        } else if (state == STATE_AUTO_COMPLETE) {
            startButton.setVisibility(VISIBLE);
            startButton.setImageResource(R.drawable.video_stop);
            replayTextView.setVisibility(VISIBLE);
        } else {
            startButton.setImageResource(R.drawable.video_stop);
            replayTextView.setVisibility(GONE);
        }
    }
}
