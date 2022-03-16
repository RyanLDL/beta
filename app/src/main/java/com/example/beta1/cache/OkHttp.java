package com.example.beta1.cache;

import android.util.Log;

import androidx.annotation.NonNull;

import com.danikula.videocache.HttpProxyCacheServer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {
    private static final String TAG="TAG";

    //原始地址
    public String rawUrl;
    //列表中的位置
    public int position;
    //VideoCache服务器
    public HttpProxyCacheServer cacheServer;
    //是否被取消
    private boolean isCancle;
    private boolean isload;

    private final static List<String> blackList = new ArrayList<>();

    //开始预加载
    public void start(){
        if (blackList.contains(rawUrl))return;
        Log.d(TAG, "start: 预加载开始"+position);
        String proxyUrl = cacheServer.getProxyUrl(rawUrl);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .get()
                .url(proxyUrl)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d(TAG, "onFailure: 预加载异常" + position + "异常信息：" + e.getMessage());
                blackList.add(rawUrl);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()){
                    byte []bytes=new byte[8 * 1024];
                    InputStream in = new BufferedInputStream(response.body().byteStream());
                    int length;
                    int read=-1;

//                    in = response .body().byteStream();
                    while ((length = in.read(bytes) )!=-1 ){
                        read += length;
                        //预加载完成或取消
                        if (isCancle || read >=PreloadManager.PRELOAD_LENGTH){
                            if (isCancle){
                                Log.d(TAG,"预加载取消：" + position + " 读取数据：" + read + " Byte");
                            }else {
                                Log.d(TAG,"预加载成功：" + position + " 读取数据：" + read + " Byte");
                            }
                            break;
                        }
                    }

                }
            }
        });

    }

    public void cancel() {
        if (isload){
            isCancle = true;
        }
    }
}
