/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.http;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.File;
import java.io.IOException;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/5/24 2:16 下午
 **/
public class OkHttpUpload {

    private static OkHttpClient client = new OkHttpClient();
    private static MediaType mediaType = MediaType.parse("");

    public static String post(String url) throws IOException {
        // 构建请求
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.MIXED);
        RequestBody body = RequestBody.create(new File("/Users/yinchao/2.jpeg"), mediaType);
        builder.addFormDataPart("file", "2.jpeg", body);
        builder.addFormDataPart("expires", "12a3");
        builder.addFormDataPart("preserve_original_filename", "true");

        // 创建requestBody
        RequestBody requestBody = builder.build();
        Request.Builder requestBuilder = new Request.Builder().url(url).post(requestBody);

        Request request = requestBuilder.build();
        Response execute = client.newCall(request).execute();
        System.out.println(execute.body().string());
        return execute.toString();
    }

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8082/webupload.php";
        System.out.println(post(url));
    }
}
