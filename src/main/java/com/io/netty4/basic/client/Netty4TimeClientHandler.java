package com.io.netty4.basic.client;

import com.io.netty5.serializable.UserInfo;
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
 * @create 2020/5/24 10:07 下午
 */
public class Netty4TimeClientHandler extends ChannelInboundHandlerAdapter {

    private int count;

    public Netty4TimeClientHandler() {
    }

    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive");
        ctx.writeAndFlush("这是一条普通消息");
    }


    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        System.out.println(++count + "---" + Thread.currentThread().getName() + ",Server return Message：" + msg);
    }

    public void exception(ChannelHandlerContext ctx, Throwable cause) {
        //释放资源
        System.out.println("error message:" + cause.getMessage());
        ctx.close();
    }


}
