/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_11.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/15 20:00
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
                            // 解码转String，注意调整自己的编码格式GBK、UTF-8
//                             基于换行符号
//                            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
//                            // 解码转String，注意调整自己的编码格式GBK、UTF-8
                            channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
                            // 解码转String，注意调整自己的编码格式GBK、UTF-8
                            channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
                            channel.pipeline().addLast(new MyClientHandler());
                        }
                    });
            ChannelFuture f = b.connect("localhost", 7397).sync();
            f.channel().writeAndFlush("hi 微信公众号：bugstack虫洞栈 | 欢迎关注并获取专题\r\n文章和源码\r\n");
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }
}
