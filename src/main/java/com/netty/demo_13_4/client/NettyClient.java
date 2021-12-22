/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_4.client;

import com.netty.demo_13_4.codec.ObjDecoder;
import com.netty.demo_13_4.codec.ObjEncoder;
import com.netty.demo_13_4.dto.FileTransferProtocol;
import com.netty.demo_13_4.dto.MessageInfo;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/21 11:43
 **/
public class NettyClient {

    // 配置服务端NIO线程组
    private EventLoopGroup workGroup = new NioEventLoopGroup();
    private Channel channel;

    public ChannelFuture connect(String host, int port) {
        ChannelFuture f = null;
        try {
            Bootstrap b = new Bootstrap();
            b.group(workGroup).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        // 解码转String，注意调整自己的编码格式GBK、UTF-8
                        pipeline.addLast(new ObjDecoder(FileTransferProtocol.class));
                        pipeline.addLast(new ObjEncoder(FileTransferProtocol.class));
                        pipeline.addLast(new NettyClientHandler());
                    }
                });

            f = b.connect(host, port).syncUninterruptibly();
            System.out.println("netty client system start done");
            this.channel = f.channel();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        return f;
    }

    public void destroy() {
        if (null == channel) return;
        channel.close();
        workGroup.shutdownGracefully();
    }
}
