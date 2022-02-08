package com.netty.demo_rpc.client;

import com.google.gson.Gson;
import com.netty.demo_rpc.domain.Response;
import com.netty.demo_rpc.future.SyncWriteFuture;
import com.netty.demo_rpc.future.SyncWriteMap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 数据格式验证
        String ret = (String) msg;
        Response response = new Gson().fromJson(ret, Response.class);
        String requestId = response.getRequestId();
        SyncWriteFuture future = (SyncWriteFuture) SyncWriteMap.syncKey.get(requestId);
        if (future != null) {
            future.setResponse(response);
        }
    }
}
