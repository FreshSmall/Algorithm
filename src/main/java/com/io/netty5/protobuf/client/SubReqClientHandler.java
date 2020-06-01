package com.io.netty5.protobuf.client;

import com.io.netty5.protobuf.SubscribeReqProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/5/26 2:09 下午
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {
    private int count;

    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive");
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(subReq(i));
        }
        System.out.println("消息发送完毕");
    }


    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        System.out.println(++count + "---" + Thread.currentThread().getName() + ",Server return Message：" + msg);
    }

    public void exception(ChannelHandlerContext ctx, Throwable cause) {
        //释放资源
        System.out.println("error message:" + cause.getMessage());
        ctx.close();
    }

    private SubscribeReqProto.SubscribeReq subReq(int i) {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqID(1);
        builder.setUserName("yinchao");
        builder.setProductName("Netty Book");
        List<String> address = new ArrayList<>();
        address.add("nanjing");
        address.add("beijing");
        address.add("shanghai");
        builder.addAllAddress(address);
        return builder.build();
    }
}
