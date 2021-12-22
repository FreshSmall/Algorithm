/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_4;

import com.netty.demo_13_4.server.NettyServer;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/22 14:26
 **/
public class NettyServerTest {

    public static void main(String[] args) {
        //启动服务
        new NettyServer().bind(8083);
    }

}
