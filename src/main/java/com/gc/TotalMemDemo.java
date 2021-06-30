/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/6/23 9:26 下午
 **/
public class TotalMemDemo {

    public static void main(String[] args) {

        List<Byte[]> list = new ArrayList<>();
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);
        long maxMemory = runtime.maxMemory() / (1024 * 1024);

        print(totalMemory, freeMemory, maxMemory);

        for (int i = 0; i < 10; i++) {
            list.add(new Byte[2 * 1024 * 1024]);
        }

        totalMemory = runtime.totalMemory() / (1024 * 1024);
        freeMemory = runtime.freeMemory() / (1024 * 1024);
        maxMemory = runtime.maxMemory() / (1024 * 1024);

        print(totalMemory, freeMemory, maxMemory);

    }

    /**
     * 打印相关的参数
     *
     * @param totalMemory
     * @param freeMemory
     * @param maxMemory
     */
    private static void print(long totalMemory, long freeMemory, long maxMemory) {
        String str = String
            .format("总内存：%sM,空闲内存：%sM，最大可用内存：%sM", totalMemory, freeMemory, maxMemory);
        System.out.println(str);
    }

}
