/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo6_mysql;

import com.netty.demo6_mysql.proto.HandshakePacket;
import com.netty.demo6_mysql.proto.OkPacket;
import com.netty.demo6_mysql.util.Capabilities;
import com.netty.demo6_mysql.util.RandomUtil;
import com.netty.demo6_mysql.version.Versions;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/14 17:28
 **/
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    public byte[] seed;

    // 用于存放用户channel信息
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 当有客服端连接时，添加到ChannelGroup通信组
        channelGroup.add(ctx.channel());
        System.out.println("连接成功建立");
        byte[] rand1 = RandomUtil.randomBytes(8);
        byte[] rand2 = RandomUtil.randomBytes(12);

        // 保存认证数据
        byte[] seed = new byte[rand1.length + rand2.length];
        System.arraycopy(rand1, 0, seed, 0, rand1.length);
        System.arraycopy(rand2, 0, seed, rand1.length, rand2.length);
        this.seed = seed;

        // 发送握手数据包
        HandshakePacket hs = new HandshakePacket();
        hs.packetId = 0;
        hs.protocolVersion = Versions.PROTOCOL_VERSION;
        hs.serverVersion = Versions.SERVER_VERSION;
        hs.threadId = 0;
        hs.seed = rand1;
        hs.serverCapabilities = getServerCapabilities();
        //hs.serverCharsetIndex = (byte) (source.charsetIndex & 0xff);
        hs.serverStatus = 2;
        hs.restOfScrambleBuff = rand2;
        System.out.println("连接建立");
        hs.write(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] b = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(b);
        for (byte b1 : b) {
            System.out.print(b1 + " ");
        }
        System.out.println("字符串信息：" + new String(b));

        //System.out.println("服务端收到的消息：" + msg);
        //channelGroup.writeAndFlush(msg);
        ByteBuf respByte = ctx.alloc().buffer().writeBytes(OkPacket.AUTH_OK);
        // just io , no need thread pool
        ctx.writeAndFlush(respByte);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 用户断开连接
        System.out.println("用户断开连接，" + ctx.channel().localAddress().toString());
        channelGroup.remove(ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息：\r\n" + cause.getMessage());
    }

    protected int getServerCapabilities() {
        int flag = 0;
        flag |= Capabilities.CLIENT_LONG_PASSWORD;
        flag |= Capabilities.CLIENT_FOUND_ROWS;
        flag |= Capabilities.CLIENT_LONG_FLAG;
        flag |= Capabilities.CLIENT_CONNECT_WITH_DB;
        // flag |= Capabilities.CLIENT_NO_SCHEMA;
        // flag |= Capabilities.CLIENT_COMPRESS;
        flag |= Capabilities.CLIENT_ODBC;
        // flag |= Capabilities.CLIENT_LOCAL_FILES;
        flag |= Capabilities.CLIENT_IGNORE_SPACE;
        flag |= Capabilities.CLIENT_PROTOCOL_41;
        flag |= Capabilities.CLIENT_INTERACTIVE;
        // flag |= Capabilities.CLIENT_SSL;
        flag |= Capabilities.CLIENT_IGNORE_SIGPIPE;
        flag |= Capabilities.CLIENT_TRANSACTIONS;
        // flag |= ServerDefs.CLIENT_RESERVED;
        flag |= Capabilities.CLIENT_SECURE_CONNECTION;
        return flag;
    }
}
