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
        Object clazz = Class.forName(request.getInterfaceName());
        String methodName = request.getMethodName();
        Method method = clazz.getClass().getMethod(methodName, String.class);
        String result = (String) method.invoke(clazz, request.getArgs());

        Response response = new Response();
        response.setResult(result);
        response.setCode(1);

        ctx.writeAndFlush(gson.toJson(response));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客服端连上");
        ctx.writeAndFlush("服务连接上了");
    }

    public static void main(String[] args) throws Exception {
        String interfaceName = "com.netty.demo_rpc.impl.HelloServiceImpl";
        Object clazz = Class.forName(interfaceName);
        String methodName = "sayHello";
        Method method = clazz.getClass().getMethod(methodName, String.class);
        Object[] arguments = new Object[]{"服务端你好"};
        String result = (String) method.invoke(clazz, arguments);
        System.out.println("服务端返回结果：" + result);
    }
}
