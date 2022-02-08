package com.netty.demo_rpc.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.netty.demo_rpc.domain.Request;
import com.netty.demo_rpc.domain.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.lang.reflect.Method;

public class NettyHandler extends ChannelInboundHandlerAdapter {

    private static final Gson gson = new GsonBuilder().create();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务端收到信息:" + msg);
        Request request = gson.fromJson((String) msg, Request.class);
        Class<?> clazz = Class.forName(request.getInterfaceName());
        String methodName = request.getMethodName();
        Method method = clazz.getMethod(methodName, String.class);
        Object obj = clazz.getConstructor(null).newInstance(null);
        String result = (String) method.invoke(obj, request.getArgs());
        Response response = new Response();
        response.setResult(result);
        response.setRequestId(request.getRequestId());
        response.setCode(1);

        ctx.writeAndFlush(gson.toJson(response));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客服端连上");
//        ctx.writeAndFlush("服务连接上了");
    }
}
