package com.example.beta1.Data;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataUtil {
    public static List<TiktokBean> videoData;

    public static List<TiktokBean> getTiktokDataFromAssets(Context context) {
        try {
            if (videoData == null) {
                InputStream is = context.getAssets().open("video_data");
                int length = is.available();
                byte[] buffer = new byte[length];
                is.read(buffer);
                is.close();
                String result = new String(buffer, Charset.forName("UTF-8"));
                videoData = TiktokBean.arrayTiktokBeanFromData(result);
            }
            return videoData;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
