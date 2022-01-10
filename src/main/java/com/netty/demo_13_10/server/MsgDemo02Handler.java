package com.netty.demo_13_10.server;

import com.netty.demo_13_10.dto.MsgDemo2;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MsgDemo02Handler extends SimpleChannelInboundHandler<MsgDemo2> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MsgDemo2 msg) throws Exception {
        System.out.println("\r\n> msg handler ing ...");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " 接收消息的处理器：" + this.getClass().getName());
        System.out.println("channelId：" + msg.getChannelId());
        System.out.println("消息内容：" + msg.getDemo01());
    }
}
