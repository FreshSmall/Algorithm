/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/9/26 16:57
 **/
public class RedisDemo1 {

    public static void main(String[] args) {
        RedisURI uri = RedisURI.create("redis://localhost/");
        // 创建客户端
        RedisClient redisClient = RedisClient.create(uri);
        // 创建连接
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        // 同步命令
        RedisCommands<String, String> redisCommands = connection.sync();
        for (int i = 0; i < 20000; i++) {
            redisCommands.set("yunying:call:back:13277088725" + i, "a");
        }
        connection.close();
        redisClient.shutdown();
    }

}
