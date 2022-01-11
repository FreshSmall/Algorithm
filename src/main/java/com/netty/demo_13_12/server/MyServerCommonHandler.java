package com.netty.demo_13_12.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelProgressiveFuture;
import io.netty.channel.ChannelProgressiveFutureListener;
import io.netty.channel.ChannelProgressivePromise;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;

public abstract class MyServerCommonHandler extends SimpleChannelInboundHandler<String> {

    private Runnable counterTask;
    private AtomicLong consumeMsgLength = new AtomicLong();
    private long priorProgress;
    protected boolean sentFlag;

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        counterTask = () -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    long length = consumeMsgLength.addAndGet(0);
                    if (length == 0) {
                        continue;
                    }
                    System.out.println("数据发送率：" + length);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        super.handlerAdded(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        sendData(ctx);
        new Thread(counterTask).start();
    }

    protected abstract void sendData(ChannelHandlerContext ctx);

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
    }


    protected ChannelProgressivePromise getChannelProgressivePromise(ChannelHandlerContext ctx, Consumer<ChannelProgressiveFuture> completedAction) {
        ChannelProgressivePromise channelProgressivePromise = ctx.newProgressivePromise();
        channelProgressivePromise.addListener(new ChannelProgressiveFutureListener() {
            @Override
            public void operationProgressed(ChannelProgressiveFuture future, long progress, long total) throws Exception {
                consumeMsgLength.addAndGet(progress - priorProgress);
                priorProgress = progress;
            }

            @Override
            public void operationComplete(ChannelProgressiveFuture future) throws Exception {
                sentFlag = false;
                if (future.isSuccess()) {
                    System.out.println("微信公众号：bugstack虫洞栈 | 提醒，消息发送成功！");
                    priorProgress -= 10;
                    Optional.ofNullable(completedAction).ifPresent(action -> action.accept(future));
                } else {
                    System.out.println("微信公众号：bugstack虫洞栈 | 提醒，消息发送失败！");
                    future.cause().printStackTrace();
                }
            }
        });
        return channelProgressivePromise;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("微信公众号：bugstack虫洞栈 | NettyServer接收到消息：" + msg);
    }
}
