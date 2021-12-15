/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo11;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author yinchao
 * @description 消息入站处理器
 * @team wuhan operational dev.
 * @date 2021/12/14 17:28
 **/
public class MyServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
        DatagramPacket packet) throws Exception {
        String str = packet.content().toString();
        System.out.println(
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ",服务端收到消息：" + str);
        // 向客户端发送消息
        String json = "服务端:我已经收到客户端发送的消息";
        ctx.writeAndFlush(json);
        // 由于数据报的数据是以字符数组传的形式存储的，所以传转数据
        // byte[] bytes = json.getBytes(Charset.forName("GBK"));
        // DatagramPacket data = new DatagramPacket(Unpooled.copiedBuffer(bytes), packet.sender());
        // ctx.writeAndFlush(data);//向客户端发送消息
    }
}
