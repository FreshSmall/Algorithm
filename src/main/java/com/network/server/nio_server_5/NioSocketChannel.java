/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_5;

import lombok.Data;

import java.nio.channels.SocketChannel;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/15 19:53
 **/
@Data
public class NioSocketChannel {

    private int readyOps;
    private SocketChannel socketChannel;
}
