/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_6;

import java.nio.channels.SocketChannel;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/11/16 14:44
 **/
public class Call {

    public final SocketChannel channel;
    public final byte[] dataBytes;

    public Call(SocketChannel channel, byte[] dataBytes) {
        this.channel = channel;
        this.dataBytes = dataBytes;
    }

}
