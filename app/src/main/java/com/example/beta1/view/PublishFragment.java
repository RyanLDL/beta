package com.example.beta1.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.beta1.R;
import com.example.beta1.tool.Glide4Engine;
import com.zhongjh.albumcamerarecorder.camera.constants.FlashModels;
import com.zhongjh.albumcamerarecorder.settings.AlbumSetting;
import com.zhongjh.albumcamerarecorder.settings.CameraSetting;
import com.zhongjh.albumcamerarecorder.settings.GlobalSetting;
import com.zhongjh.albumcamerarecorder.settings.MultiMediaSetting;
import com.zhongjh.common.entity.SaveStrategy;
import com.zhongjh.common.enums.MimeType;
import com.zhongjh.videoedit.VideoMergeManager;

import java.util.Set;

public class PublishFragment extends Fragment {
    private static final String TAG = "TAG";
    private View view;
    AlbumSetting albumSetting;
    GlobalSetting globalSetting;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

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
       while (true){
           open();
           break;
       }

        return view;
    }

    private void open() {
        CameraSetting cameraSetting = initCameraSetting();
        albumSetting = initAlbumSetting();
        Set<MimeType> mimeTypes = MimeType.ofAll();
        globalSetting = MultiMediaSetting.from(getActivity()).choose(mimeTypes);
        globalSetting.albumSetting(albumSetting);
        globalSetting.cameraSetting(cameraSetting);
        globalSetting.defaultPosition(1);
        globalSetting.isCutscenes(true);
        globalSetting.isImageEdit(true);
        globalSetting.setOnMainListener(errorMessage -> {
            Log.d(TAG, errorMessage);
            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_LONG).show();
        })
                .allStrategy(new SaveStrategy(true,"com.example.beta1.fileprovider","beta"))
                .imageEngine(new Glide4Engine())
                .maxSelectablePerMediaType(null,5,3,3,0,0,0)
                .forResult(236);
    }

    private AlbumSetting initAlbumSetting() {
        AlbumSetting albumSetting=new AlbumSetting(false);
        Set<MimeType> mimeTypes;
        mimeTypes=MimeType.ofAll();
        albumSetting.mimeTypeSet(mimeTypes);
        albumSetting.showSingleMediaType(false)
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
    public void onDestroy() {
        super.onDestroy();
        if (globalSetting != null) {
            globalSetting.onDestroy();
        }
    }
}