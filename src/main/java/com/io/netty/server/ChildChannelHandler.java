package com.io.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/5/24 7:09 下午
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

    ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());

    @Override
    protected void initChannel(SocketChannel arg) throws Exception {
//        arg.pipeline().addLast(new LineBasedFrameDecoder(1024)); 换行分割符
//        arg.pipeline().addLast(new DelimiterBasedFrameDecoder(1024,delimiter)); 特殊字符分割符
        arg.pipeline().addLast(new FixedLengthFrameDecoder(20));
        arg.pipeline().addLast(new StringDecoder());
        arg.pipeline().addLast(new TimeServerHandler());
    }
}
