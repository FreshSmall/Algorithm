/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_10.client;

import com.netty.demo_13_10.codec.ObjDecoder;
import com.netty.demo_13_10.codec.ObjEncoder;
import com.netty.demo_13_10.util.MsgUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
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

    public void bind() {
        ChannelFuture f = null;
        try {
            Bootstrap b = new Bootstrap();
            b.group(workGroup).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            ChannelPipeline pipeline = channel.pipeline();
                            // 解码转String，注意调整自己的编码格式GBK、UTF-8
                            pipeline.addLast(new ObjDecoder());
                            pipeline.addLast(new ObjEncoder());
                            pipeline.addLast(new NettyClientHandler());
                        }
                    });

            f = b.connect("localhost", 8083).syncUninterruptibly();
            //测试消息，分别发放demo01、demo02、demo03
            f.channel().writeAndFlush(MsgUtil.buildMsgDemo01(f.channel().id().toString(), "你好，消息体MsgDemo01，我是https://bugstack.cn博主，付政委。这是我的公众号<bugstack虫洞栈>，欢迎关注我获取案例源码。"));
            f.channel().writeAndFlush(MsgUtil.buildMsgDemo02(f.channel().id().toString(), "你好，消息体MsgDemo02，我是https://bugstack.cn博主，付政委。这是我的公众号<bugstack虫洞栈>，欢迎关注我获取案例源码。"));
            f.channel().writeAndFlush(MsgUtil.buildMsgDemo03(f.channel().id().toString(), "你好，消息体MsgDemo03，我是https://bugstack.cn博主，付政委。这是我的公众号<bugstack虫洞栈>，欢迎关注我获取案例源码。"));
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyClient().bind();
    }

}
