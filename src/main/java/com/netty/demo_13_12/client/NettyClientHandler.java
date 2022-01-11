package com.netty.demo_13_12.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.UUID;

public class NettyClientHandler extends SimpleChannelInboundHandler<String> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("微信公众号：bugstack虫洞栈 | NettyClient接收到消息：" + msg + " length：" + msg.length());
        ctx.write(UUID.randomUUID().toString() + "\r\n", ctx.voidPromise());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.channel().close();
    }
}
