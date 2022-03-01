package com.example.beta1.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.beta1.Adapter.HomeAdapter;
import com.example.beta1.R;

import cn.jzvd.Jzvd;

public class HomeFragment extends Fragment {
    private RecyclerView rv;
    private HomeAdapter adapter;
    private MyLayoutManager layoutManager;
    private int currentPosition=-1;
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view==null){
            view=inflater.inflate(R.layout.fragment_home,container,false);
        }
        initView();
        return view;

    }

    private void initView() {
        rv = view.findViewById(R.id.rv);
        adapter=new HomeAdapter();
        layoutManager=new MyLayoutManager(getContext(), OrientationHelper.VERTICAL,false);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
        layoutManager.setListener(new OnViewPagerListener() {
            @Override
            public void onInitComplete() {
                //自动播放第一条
                autoPlayVideo(0);
            }

            @Override
            public void onPageRelease(boolean isNext, int position) {
                if (currentPosition == position){
                    Jzvd.releaseAllVideos();//释放视频
                }
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                if (currentPosition == position){
                    return;
                }
                autoPlayVideo(position);
                currentPosition=position;

            }
        });
    rv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(@NonNull View view) {

        }

        @Override
        public void onChildViewDetachedFromWindow(@NonNull View view) {
            Jzvd jzvd = view.findViewById(R.id.videoview);
            if (jzvd != null && Jzvd.CURRENT_JZVD != null && jzvd.jzDataSource != null &&
            jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl())){
                if (Jzvd.CURRENT_JZVD !=null && Jzvd.CURRENT_JZVD.screen !=Jzvd.SCREEN_FULLSCREEN){
                    Jzvd.releaseAllVideos();
                }
            }
        }
    });

    }
    public void autoPlayVideo(int position){
        if (rv == null || rv.getChildAt(0) == null){
            return;
        }
        VideoView videoView=rv.getChildAt(0).findViewById(R.id.videoview);
        if (videoView != null){
            Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);
            videoView.startVideoAfterPreloading();
        }
    }
//    @Override
//    public void onBackPressed() {
//        if (Jzvd.backPress()) {
//            return;
//        }
//        onBackPressed();
//    }

    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}