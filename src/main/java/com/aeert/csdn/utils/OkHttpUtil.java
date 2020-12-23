package com.aeert.csdn.utils;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.io.IOException;

/**
 * @Author l'amour solitaire
 * @Description TODO
 * @Date 2020/11/13 上午9:49
 **/
public class OkHttpUtil {

    static MediaType mediaType = MediaType.parse("application/json");

    /**
     * Get Send
     **/
    public static String sendGet(String url) throws IOException {
        Request request = new Request.Builder()
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Accept-Language", "zh-CN,zh;q=0.8")
                .header("Connection", "keep-alive")
                .header("Host", "yunkun.blog.csdn.net")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36")
                .url(url).build();
        Call call = SpringUtils.getBean(OkHttpClient.class).newCall(request);
        return call.execute().body().string();
    }

}