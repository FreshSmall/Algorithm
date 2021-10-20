/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_2;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/10/19 16:55
 **/
public class RequestChannel {

    // 请求队列
    private static final Queue requestQueue = new ArrayBlockingQueue<>(5);

    // 响应队列
    private static final Map<String, ArrayBlockingQueue<String>> responseQueue = new ConcurrentHashMap<>();

    public String receiveResponse(String id) {
       return responseQueue.get(id).poll();
    }
}
