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
 * @date 2021/9/30 16:25
 **/
public class AccountMain {

    public static void main(String[] args) throws InterruptedException {
        for (;;) {
            new Account().operation();
            Thread.sleep(5000);
        }
    }

}
