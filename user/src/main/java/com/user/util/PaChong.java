package com.user.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PaChong {
    public static void main(String[] args) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet("https://www.yuncai998.com/supply/gotoBusiness");

            CloseableHttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String content =  EntityUtils.toString(entity,"utf-8");
            response.close();
            Document parse = Jsoup.parse(content);
            System.out.println(parse);
            Map<Object, Object> concurrentHashMap = new ConcurrentHashMap<>();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
