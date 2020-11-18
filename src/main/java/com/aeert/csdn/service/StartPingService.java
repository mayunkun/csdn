package com.aeert.csdn.service;

import com.aeert.csdn.utils.OkHttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Author l'amour solitaire
 * @Description TODO
 * @Date 2020/11/18 下午2:29
 **/
@Slf4j
@Component
public class StartPingService implements CommandLineRunner {

    @Value("${blog.url:https://blog.csdn.net/kuangni5808/article/list/}")
    private String blogUrl;

    @Value("${blog.keyWord:kuangni5808/article}")
    private String keyWord;

    @Override
    public void run(String... args) throws Exception {
        deal();
    }

    private void deal() throws IOException {
        List<String> urls = urls(blogUrl, 1, new ArrayList<>());
        urls.stream().forEach(m -> {
            try {
                Thread.sleep(new Random().nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                OkHttpUtil.sendGet(m);
                log.info("链接{} 访问成功！", m);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        deal();
    }

    private List<String> urls(String url, Integer page, List<String> result) throws IOException {
        Document document = Jsoup.parse(new URL(url + page).openStream(), "UTF-8", url + page);
        Elements tables = document.getElementsByClass("article-list").select("a");
        List<String> urls = tables.stream().filter(m -> m.attr("href").contains(keyWord)).map(m -> m.attr("href")).collect(Collectors.toList());
        result.addAll(urls);
        if (urls.size() > 0) {
            return urls(url, page + 1, result);
        }
        return result;
    }



}