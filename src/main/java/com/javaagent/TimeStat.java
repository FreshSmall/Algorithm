/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.javaagent;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/9/30 16:43
 **/
public class TimeStat {

    static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void start() {
        threadLocal.set(System.currentTimeMillis());
    }

    public static void end() {
        long time = System.currentTimeMillis() - threadLocal.get();
        System.out.println(Thread.currentThread().getStackTrace()[2] + "方法耗时：");
        System.out.println(time + "毫秒");
    }

}
