package com.io.netty4.basic.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/5/24 7:22 下午
 */
public class Netty4TimeServerHandler extends ChannelInboundHandlerAdapter {

    private int count;

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println(++count + "--->" + Thread.currentThread().getName() + ",The server receive message : " + msg);
        ctx.writeAndFlush(msg);
    }


    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

}
