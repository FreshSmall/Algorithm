/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_7.server;

import com.netty.demo_13_7.msg.Request;
import com.netty.demo_13_7.msg.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.util.Date;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/21 13:56
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("连接开始");
        String str = "通知服务端链接建立成功" + ",时间:" + new Date() + ",地址:" + channel.localAddress().getHostString();
        System.out.println(str);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request request = (Request) msg;
        Response response = new Response();
        response.setRequestId(request.getRequestId());
        response.setParam("服务端收到客户端的相关消息：" + request.getResult());
        ctx.writeAndFlush(response);
        //释放
        ReferenceCountUtil.release(request);

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开链接，" + ctx.channel().localAddress().toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息!\r\n" + cause.getMessage());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
