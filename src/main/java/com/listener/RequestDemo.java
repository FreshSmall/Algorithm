/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.listener;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/10/11 11:22
 **/
public class RequestDemo {

    public static void main(String[] args) {
        RequestFuture future = new RequestFuture();
        future.addListener(new RequestFutureListener() {

            @Override
            public void onSuccess() {
                System.out.println("成功");
            }

            @Override
            public void onFail() {
                System.out.println("失败");
            }
        });
        System.out.println("继续上课....");
    }

}
