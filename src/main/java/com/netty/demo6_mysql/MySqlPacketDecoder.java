package com.netty.demo6_mysql;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MySqlPacketDecoder extends ByteToMessageDecoder {

    /**
     * MySql外层结构解包
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readableBytes();
        System.out.println("length:" + length);
        in.resetReaderIndex();
    }
}
