package com.netty.demo_rpc.proxy;

import com.netty.demo_rpc.domain.Request;
import com.netty.demo_rpc.domain.Response;
import com.netty.demo_rpc.future.SyncWrite;
import io.netty.channel.ChannelFuture;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxyHandler implements InvocationHandler {

    private Request request;

    private ChannelFuture channelFuture;

    public JdkProxyHandler(Request request, ChannelFuture channelFuture) {
        this.request = request;
        this.channelFuture = channelFuture;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SyncWrite s = new SyncWrite();
        Response response = s.writeAndSync(channelFuture.channel(), request, 1000);
        return response.getResult();
    }

    private Request buildRequest() {
        Request request = new Request();
        request.setMethodName("sayHello");
        request.setInterfaceName("com.netty.demo_rpc.impl.HelloServiceImpl");
        request.setArgs(new Object[]{"服务端你好"});
        return request;
    }
}
