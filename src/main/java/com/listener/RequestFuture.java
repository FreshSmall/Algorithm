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
 * @date 2021/10/11 11:20
 **/
public class RequestFuture {

    private boolean flag = true;

    public void addListener(RequestFutureListener listener) {
        if (flag) {
            listener.onSuccess();
        } else {
            listener.onFail();
        }
    }

}
