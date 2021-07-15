/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.stream;

import org.apache.tika.Tika;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * @author yinchao
 * @description 测试输入流
 * @team wuhan operational dev.
 * @date 2020/8/31 2:45 下午
 **/
public class InputStreamDemo {

    Tika tika = new Tika();

    public void testUrlStream() throws Exception {
        String url = "https://genshuixue-public-test.oss-cn-beijing.aliyuncs.com/expiration_12d/2161928_fmfzy33u.jpeg";
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        InputStream inputStream = yc.getInputStream();
        System.out.println("first available is " + inputStream.available());
        System.out.println("second available is " + inputStream.available());

        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        System.out.println("third available is " + bufferedInputStream.available());

        bufferedInputStream.mark(65536);
        bufferedInputStream.reset();

        System.out.println("four available is " + inputStream.available());


        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream is missing");
        } else {
            byte[] bytes = new byte[65536];
            int totalRead = 0;

            for (int lastRead = inputStream.read(bytes); lastRead != -1; lastRead = inputStream.read(bytes, totalRead, bytes.length - totalRead)) {
                totalRead += lastRead;
                if (totalRead == bytes.length) {

                }
            }

            byte[] shorter = new byte[totalRead];
            System.arraycopy(bytes, 0, shorter, 0, totalRead);
        }

        System.out.println("six available is " + inputStream.available());
    }

    public void testFileInputSteam() throws Exception {
        File file = new File("/Users/yinchao/1.jpeg");
        FileInputStream inputStream = new FileInputStream(file);
        System.out.println("first available is " + inputStream.available());
        System.out.println(tika.detect(inputStream));
        System.out.println("second available is " + inputStream.available());
    }


    public static void main(String[] args) throws Exception {
        InputStreamDemo demo = new InputStreamDemo();
        demo.testFileInputSteam();
        System.out.println(URLEncoder.encode("测试.xlsx","UTF-8"));
        System.out.println(new String("测试".getBytes("UTF-8"),"ISO-8859-1"));
    }
}
