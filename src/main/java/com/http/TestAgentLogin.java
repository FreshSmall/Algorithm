/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/29 17:46
 **/
public class TestAgentLogin {

    private static OkHttpClient client = new OkHttpClient();
    private static CountDownLatch countDownLatch = new CountDownLatch(50);

    static class BossThread implements Runnable {

        @SneakyThrows
        @Override
        public void run() {
            String url = "http://localhost:12500/agent/login";
            // 构建请求
            MediaType mediaType = MediaType.get("application/json; charset=utf-8");
            Map<String, Object> param = new HashMap<>();
            param.put("businessId", "c2ac");
            param.put("bindTel", "13277088725");
            param.put("cno", "3001");
            param.put("bindType", 1);

            // 创建requestBody
            RequestBody requestBody = RequestBody.create(JSONObject.toJSONString(param), mediaType);
            Request request1 = new Request.Builder().url(url).post(requestBody).build();
            countDownLatch.await();
            Response execute = client.newCall(request1).execute();
            System.out.println(execute.body().string());
        }
    }

    /*public void testAgentLogin(String url) throws IOException {
        // 构建请求
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        Map<String, Object> param = new HashMap<>();
        param.put("businessId", "c2ac");
        param.put("bindTel", "13277088725");
        param.put("cno", "3001");
        param.put("bindType", 1);

        // 创建requestBody
        RequestBody requestBody = RequestBody.create(JSONObject.toJSONString(param), mediaType);
        Request request1 = new Request.Builder().url(url).post(requestBody).build();
        Response execute = client.newCall(request1).execute();
        System.out.println(execute.body().string());
    }*/

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 50; i++) {
            countDownLatch.countDown();
            new Thread(new BossThread()).start();
        }
    }

}
