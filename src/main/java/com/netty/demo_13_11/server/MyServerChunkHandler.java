package com.netty.demo_13_11.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.util.ReferenceCountUtil;

public class MyServerChunkHandler extends ChannelOutboundHandlerAdapter {

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        // 内容验证
        if (!(msg instanceof ByteBuf)) {
            super.write(ctx, msg, promise);
            return;
        }
        //获取Byte
        ByteBuf buf = (ByteBuf) msg;
        byte[] data = this.getData(buf);
        //写入流中
//        ByteInputStream in = new ByteInputStream();
//        in.setBuf(data);
        //消息分块；10个字节，测试过程中可以调整
//        ChunkedStream stream = new ChunkedStream(in, 10);
        ChannelProgressivePromise channelProgressivePromise = ctx.channel().newProgressivePromise();
        channelProgressivePromise.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture channelProgressiveFuture, long l, long l1) throws Exception {

            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("数据上传成功");
                    promise.setSuccess();
                } else {
                    System.out.println("数据上传失败");
                    promise.setFailure(future.cause());
                }
            }
        });
        ReferenceCountUtil.release(msg);
//        ctx.writeAndFlush(stream, channelProgressivePromise);
    }

    /**
     * 获取Byte
     *
     * @param buf
     * @return
     */
    private byte[] getData(ByteBuf buf) {
        if (buf.hasArray()) {
            return buf.array().clone();
        }
        byte[] data = new byte[buf.readableBytes() - 1];
        buf.readBytes(data);
        return data;
    }
}
