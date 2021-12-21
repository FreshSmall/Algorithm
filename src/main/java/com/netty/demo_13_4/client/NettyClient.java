/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_4.client;

import com.netty.demo_13_4.codec.ObjDecoder;
import com.netty.demo_13_4.codec.ObjEncoder;
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

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        // 解码转String，注意调整自己的编码格式GBK、UTF-8
                        pipeline.addLast(new ObjDecoder(MessageInfo.class));
                        pipeline.addLast(new ObjEncoder(MessageInfo.class));
                        pipeline.addLast(new NettyClientHandler());
                    }
                });
            ChannelFuture f = b.connect("127.0.0.1", 8083).sync();
            System.out.println("netty client system start done");
            f.channel().writeAndFlush(new MessageInfo(f.channel().id().toString(), "发送服务端相关信息"));
            f.channel().writeAndFlush(new MessageInfo(f.channel().id().toString(), "发送服务端相关信息"));
            f.channel().writeAndFlush(new MessageInfo(f.channel().id().toString(), "发送服务端相关信息"));
            f.channel().writeAndFlush(new MessageInfo(f.channel().id().toString(), "发送服务端相关信息"));
            f.channel().writeAndFlush(new MessageInfo(f.channel().id().toString(), "发送服务端相关信息"));
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
