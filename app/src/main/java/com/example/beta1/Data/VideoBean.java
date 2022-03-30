package com.example.beta1.Data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
//
//{
//        "id": "7038044977200483614",
//        "title": "在距离成都不到4小时的巴郎山，我们又一次被眼前的美景征服了～#阿坝红了 @阿坝旅游 ",
//        "type": 4,
//        "uid": "wgnc",
//        "ulid": "91562060689",
//        "usid": "286368554",
//        "username": "暴走夫妻",
//        "signature": "阿元👫小夏\n一直在路上！\n商务V：A756062508",
//        "avatar": "https://p6.douyinpic.com/aweme/100x100/aweme-avatar/mosaic-legacy_2d4c70000aadbcf4afcf6.jpeg?from=116350172",
//        "avatar_large": "https://p6.douyinpic.com/aweme/1080x1080/aweme-avatar/mosaic-legacy_2d4c70000aadbcf4afcf6.jpeg?from=116350172",
//        "image": "https://p9-sign.douyinpic.com/obj/tos-cn-i-dy/3f0ea9329d004303af998a635d1521b5?x-expires=1649707200&x-signature=bp3Z9ryJhU9TLsejY1KVTPLfaRU%3D&from=4257465056_large",
//        "cover": "https://p3-sign.douyinpic.com/tos-cn-i-dy/3f0ea9329d004303af998a635d1521b5~c5_300x400.jpeg?x-expires=1649707200&x-signature=SLghUhXUvyoimM8qYDuX8bnLmIU%3D&from=4257465056_large&s=PackSourceEnum_DOUYIN_REFLOW&se=false&sc=cover&l=2022032904142101021214203701266FD1",
//        "poster": "https://p3-sign.douyinpic.com/tos-cn-p-0015/17ec732e75cf4f2fb83aece2231ff2e2_1638672606~tplv-dy-360p.jpeg?x-expires=1649707200&x-signature=UdUf%2FNikQcexIcvrKdioRcH4DzE%3D&from=4257465056&se=false&biz_tag=feed_cover&l=2022032904142101021214203701266FD1",
//        "video": "https://aweme.snssdk.com/aweme/v1/playwm/?video_id=v0200fg10000c6m1kkrc77ucfpabp5g0&ratio=720p&line=0",
//        "videox": "https://aweme.snssdk.com/aweme/v1/play/?video_id=v0200fg10000c6m1kkrc77ucfpabp5g0&ratio=720p&line=0",
//        "audio": "https://sf3-cdn-tos.douyinstatic.com/obj/ies-music/7038045214787291918.mp3",
//        "audio_image": "https://p26.douyinpic.com/img/aweme-avatar/mosaic-legacy_2d4c70000aadbcf4afcf6~c5_168x168.jpeg?from=116350172",
//        "duration": 51416,
//        "desc": "在距离成都不到4小时的巴郎山，我们又一次被眼前的美景征服了～#阿坝红了 @阿坝旅游 ",
//        "images": null,
//        "time": 1638672599,
//        "date": "2021-12-05",
//        "long_video": null,
//        "like": 22574,
//        "reply": 520,
//        "view": 0
//        }
public class VideoBean {
    public String title;
    public String image;
    public String username;
    public String like;
    public String videox;


    public static List<VideoBean> arrayTiktokBeanFromData(String str) {

        Type listType = new TypeToken<ArrayList<VideoBean>>() {
        }.getType();

        return new Gson().fromJson(str, listType);
    }
}
