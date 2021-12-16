/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo12;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.LastHttpContent;

import java.nio.charset.Charset;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/14 17:28
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当有客服端连接时，添加到ChannelGroup通信组
        ctx.writeAndFlush("连接建立成功");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            DefaultHttpRequest request = (DefaultHttpRequest) msg;
            String uri = request.getUri();
            System.out.println("uri:" + uri);
        }

        if (msg instanceof HttpContent) {
            LastHttpContent httpContent = (LastHttpContent) msg;
            ByteBuf content = httpContent.content();
            if (!(content instanceof EmptyByteBuf)) {
                // 接收 msg 消息
                byte[] msgData = new byte[content.readableBytes()];
                content.readBytes(msgData);
                System.out.println("读取的内容：" + new String(msgData, Charset.forName("GBK")));
            }
        }

        String sendMsg =
            "微信公众号：bugstack虫洞栈，欢迎您的关注&获取源码！不平凡的岁月终究来自你每日不停歇的刻苦拼搏，每一次真正成长都因看清脚下路而抉择出的生活。愿你我；承遇朝霞，年少正恰，整装戎马，刻印风华。\n"
                +
                "博客栈：https://bugstack.cn\n" +
                "内容介绍：本公众号会每天定时推送科技资料；专题、源码、书籍、视频、咨询、面试、环境等方面内容。尤其在技术专题方面会提供更多的原创内容，让更多的程序员可以从最基础开始了解到技术全貌，目前已经对外提供的有；《手写RPC框架》、《用Java实现JVM》、《基于JavaAgent的全链路监控》、《Netty案例》等专题。\n"
                +
                "获取源码：\n" +
                "关注｛bugstack虫洞栈｝公众号获取源码，回复<用Java实现jvm源码>\n" +
                "关注｛bugstack虫洞栈｝公众号获取源码，回复<netty源码>\n" +
                "关注｛bugstack虫洞栈｝公众号获取源码，回复<rpc源码>\n" +
                "关注｛bugstack虫洞栈｝公众号获取源码，回复<基于JavaAgent的全链路监控>";
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
            HttpResponseStatus.OK,
            Unpooled.wrappedBuffer(sendMsg.getBytes(Charset.forName("UTF-8"))));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain;charset=UTF-8");
        response.headers().set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        response.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        ctx.writeAndFlush(response);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 用户断开连接
        System.out.println("用户断开连接，" + ctx.channel().localAddress().toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
