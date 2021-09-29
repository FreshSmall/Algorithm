/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.javaagent;

import java.lang.instrument.Instrumentation;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/9/29 19:33
 **/
public class PreDemo {

    public static void premain(String args, Instrumentation inst) {
        for (int i = 0; i < 10; i++) {
            System.out.println("I am premain!");
        }
    }
}
