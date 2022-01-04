package com.netty.demo_13_8.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

import java.util.Date;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        String str = "客户端:" + channel.localAddress().getHostString() + ",连接了服务端";
        System.out.println(str);
        ctx.writeAndFlush(str);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接断开！");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("服务端出现异常，错误:" + cause.getMessage());
    }


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        super.userEventTriggered(ctx, evt);
        if (evt instanceof IdleStateEvent) {
            System.out.println("测试进入");
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("reader idle");
                ctx.writeAndFlush("读取时间，客户端在吗？[ctx.close()]{我结尾是一个换行符用于处理半包粘包}");
                ctx.close();
            } else if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println("writer idle");
                ctx.writeAndFlush("写入时间，客户端在吗？{我结尾是一个换行符用于处理半包粘包}");
            } else if (event.state() == IdleState.ALL_IDLE) {
                System.out.println("all idle");
                ctx.writeAndFlush("所有时间，客户端在吗？{我结尾是一个换行符用于处理半包粘包}");
            }
        }
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端收到消息:" + msg);
        String str = "时间:" + new Date() + ",服务端已经收到消息。msg:" + msg;
        ctx.writeAndFlush(str);
    }
}
