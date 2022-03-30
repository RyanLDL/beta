package com.example.beta1.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.beta1.Adapter.newAdapter;
import com.example.beta1.Data.DataUtil;
import com.example.beta1.Data.TiktokBean;
import com.example.beta1.R;
import com.example.beta1.cache.PreloadManager;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZDataSource;
import cn.jzvd.Jzvd;


public class BlankFragment extends Fragment {

   private RecyclerView rv;
   private newAdapter ad;
   private List<TiktokBean> videoList=new ArrayList<>();
   private View view;
   private int currentPosition;
   private MyLayoutManager lm;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view==null){

            view=inflater.inflate(R.layout.fragment_home,container,false);

        }

        initView();

        addData(null);

        return view;
    }

    private void initView() {

        rv = view.findViewById(R.id.rv);

        ad=new newAdapter(videoList);
        lm=new MyLayoutManager(getContext(), OrientationHelper.VERTICAL,false);

        rv.setLayoutManager(lm);

        rv.setAdapter(ad);

        lm.setListener(new MyListener() {
            @Override
            public void onInitComplete() {
                startPlay(0);
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
                startPlay(position);
            }
        });
    }
    public void startPlay(int position){
        if (rv == null || rv.getChildAt(0) == null){
            return;
        }
        VideoView videoView=rv.getChildAt(0).findViewById(R.id.videoview);
        if (videoView != null){
            Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);
            TiktokBean bean= videoList.get(position);
            String url = PreloadManager.getInstance(getContext()).getPlayUrl(bean.videoDownloadUrl);
            JZDataSource jzDataSource=new JZDataSource(url);
            jzDataSource.looping=true;
            videoView.setUp(jzDataSource, Jzvd.SCREEN_NORMAL);
            videoView.startVideo();
            currentPosition=position;
        }
    }
    private void addData(View view){
        videoList.addAll(DataUtil.getTiktokDataFromAssets(getContext()));
        ad.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

}