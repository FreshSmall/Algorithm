package com.io.netty.protobuf.server;

import com.io.netty.protobuf.SubscribeReqProto;
import com.io.netty.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/5/26 2:00 下午
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {
    private int count;

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        System.out.println(++count + "--->" + Thread.currentThread().getName() + ",The server receive  order : " + msg);
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
        if ("yinchao".equalsIgnoreCase(req.getUserName())) {
            System.out.println("Service accept client subscribe req:["+req.toString()+"]");
            ctx.writeAndFlush(resp(req.getSubReqID()));
        }
    }


    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        ctx.close();
    }

    private SubscribeRespProto.SubscribeResp resp(int subReqID){
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
        builder.setSubReqID(subReqID);
        builder.setRespCode(0);
        builder.setDesc("Netty book order success,3 day later,sent to the designated address");
        return builder.build();
    }
}
