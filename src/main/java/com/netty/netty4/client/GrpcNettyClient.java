package com.netty.netty4.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.net.InetSocketAddress;

public class GrpcNettyClient {

    public static void main(String[] args) {
        EventLoopGroup group = new NioEventLoopGroup(1, new DefaultThreadFactory("server3", true));
        Bootstrap b = new Bootstrap();
        b.group(group);
        b.channel(NioSocketChannel.class);
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.handler(new Netty4TimeClientHandler());
        ChannelFuture regFuture = b.register();
        Channel channel = regFuture.channel();
        if (channel == null) {
            System.out.println("channel 为空");
        } else {
            channel.connect(InetSocketAddress.createUnresolved("localhost", 8083));
            channel.writeAndFlush("ping pong").addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()) {
                        System.out.println("client 连接 server 端失败");
                    }
                }
            });
        }


    }
}
