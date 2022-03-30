package com.example.beta1.view;

import static com.zhongjh.common.utils.ThreadUtils.runOnUiThread;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.beta1.R;
import com.example.beta1.tool.Glide4Engine;
import com.zhongjh.albumcamerarecorder.camera.constants.FlashModels;
import com.zhongjh.albumcamerarecorder.settings.AlbumSetting;
import com.zhongjh.albumcamerarecorder.settings.CameraSetting;
import com.zhongjh.albumcamerarecorder.settings.GlobalSetting;
import com.zhongjh.albumcamerarecorder.settings.MultiMediaSetting;
import com.zhongjh.combined.Combined;
import com.zhongjh.common.entity.LocalFile;
import com.zhongjh.common.entity.SaveStrategy;
import com.zhongjh.common.enums.MimeType;
import com.zhongjh.progresslibrary.entity.MultiMediaView;
import com.zhongjh.progresslibrary.listener.AbstractMaskProgressLayoutListener;
import com.zhongjh.progresslibrary.listener.MaskProgressLayoutListener;
import com.zhongjh.progresslibrary.widget.MaskProgressLayout;
import com.zhongjh.videoedit.VideoMergeManager;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

public class PublishFragment extends Fragment {
    private static final String TAG = "TAG";
    private View view;
    AlbumSetting albumSetting;
    GlobalSetting globalSetting;
    Combined combined;
    protected HashMap<MultiMediaView, MyTask> timers = new HashMap<>();
    MaskProgressLayout maskProgressLayout;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_publish,container,false);
        }

        maskProgressLayout = view.findViewById(R.id.img);
        open();
        return view;
    }
    private void open() {
        CameraSetting cameraSetting = initCameraSetting();
        albumSetting = initAlbumSetting();
        Set<MimeType> mimeTypes = MimeType.ofAll();
        globalSetting = MultiMediaSetting.from(this).choose(mimeTypes);
        globalSetting.albumSetting(albumSetting);
        globalSetting.cameraSetting(cameraSetting);
        globalSetting.defaultPosition(1);
        globalSetting.isCutscenes(true);
        globalSetting.isImageEdit(true);
        globalSetting
                .allStrategy(new SaveStrategy(true,"com.example.beta1.fileprovider","beta"))
                .imageEngine(new Glide4Engine())
                .maxSelectablePerMediaType(null,5,3,
                        0,0,0,0)
                .forResult(236);
        combined = new Combined(getActivity(),236,globalSetting,maskProgressLayout,
                new AbstractMaskProgressLayoutListener(){
            @Override
            public void onItemStartUploading(@NotNull MultiMediaView multiMediaView) {
                super.onItemStartUploading(multiMediaView);
                Log.d("onItemStartUploading", "onItemStartUploading");
                // 开始模拟上传
                MyTask timer = new MyTask(multiMediaView);
                timers.put(multiMediaView, timer);
                timer.schedule();
            }

            @Override
            public void onItemClose(@NotNull View view, @NotNull MultiMediaView multiMediaView) {
                super.onItemClose(view, multiMediaView);
                // 停止上传
                MyTask myTask = timers.get(multiMediaView);
                if (myTask != null) {
                    Log.d("onItemClose", "取消");
                    myTask.cancel();
                    timers.remove(multiMediaView);
                }
            }
        });
    }

    private AlbumSetting initAlbumSetting() {
        AlbumSetting albumSetting=new AlbumSetting(false);
        Set<MimeType> mimeTypes;
        mimeTypes=MimeType.ofAll();
        albumSetting.mimeTypeSet(mimeTypes);
        albumSetting
                .countable(true)
                .thumbnailScale(0.85f)
                .setOnSelectedListener(localFiles -> {
                    Log.d(TAG,  "onSelected: localFiles.size()=" + localFiles.size());
                })
                .originalEnable(true)
                .maxOriginalSize(5)
                .setOnCheckedListener(isChecked -> {
                    Log.d(TAG, "onCheck: isChecked=" + isChecked);
                });
        return albumSetting;

    }
    private CameraSetting initCameraSetting() {
        CameraSetting cameraSetting=new CameraSetting();
        Set<MimeType> mimeTypes;
        mimeTypes = MimeType.ofAll();//设置支持图片和视频
        cameraSetting.mimeTypeSet(mimeTypes);

        cameraSetting.duration(20);//最长录制时间
        cameraSetting.minDuration(1000);//最短录制时间，ms
        cameraSetting.videoMerge(new VideoMergeManager());//视频分段录制
        cameraSetting.flashModel(FlashModels.TYPE_FLASH_OFF);//默认闪光灯
        return cameraSetting;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        List<LocalFile> result = MultiMediaSetting.obtainLocalFileResult(data);
        try {
            combined.onActivityResult(requestCode,data);
        }catch (Throwable e){

        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (globalSetting != null) {
            globalSetting.onDestroy();
            maskProgressLayout.onDestroy();
        }
    }
    protected class MyTask extends Timer {

        // 百分比
        int percentage = 0;
        MultiMediaView multiMedia;

        public MyTask(MultiMediaView multiMedia) {
            this.multiMedia = multiMedia;
        }

        public void schedule() {
            this.schedule(new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(() -> {
                        percentage++;
                        multiMedia.setPercentage(percentage);
                        Log.d("MyTask", multiMedia.getUri().toString() + "进度： " + percentage);
                        if (percentage == 100) {
                            this.cancel();
                        }
                        // 现实应用设置完成赋值url的时候可以这样写如下代码：
//                        // 赋值完成
//                        multiMedia.setUrl(url);
//                        multiMedia.setPercentage(100);
                    });
                }
            }, 1000, 100);
        }

    }
}