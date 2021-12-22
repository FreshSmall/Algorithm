/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_4;

import com.netty.demo_13_4.client.NettyClient;
import com.netty.demo_13_4.dto.FileTransferProtocol;
import com.netty.demo_13_4.util.MsgUtil;
import io.netty.channel.ChannelFuture;

import java.io.File;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/22 14:05
 **/
public class NettyClientTest {

    public static void main(String[] args) {
        //启动客户端
        ChannelFuture channelFuture = new NettyClient().connect("127.0.0.1", 8083);

        //文件信息{文件大于1024kb方便测试断点续传}
        File file = new File("/Users/bjhl/test.zip");
        FileTransferProtocol fileTransferProtocol = MsgUtil
            .buildRequestTransferFile(file.getAbsolutePath(), file.getName(), file.length());
        //发送信息；请求传输文件
        channelFuture.channel().writeAndFlush(fileTransferProtocol);
    }

}
