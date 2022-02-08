package com.netty.demo_rpc.proxy;


import com.netty.demo_rpc.domain.Request;
import io.netty.channel.ChannelFuture;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class JDKProxy {

    public static <T> T getProxy(Request request, ChannelFuture channelFuture) throws Exception {
        InvocationHandler handler = new JdkProxyHandler(request, channelFuture);
        String interfaceName = request.getApiName();
        System.out.println("interfaceName:" + interfaceName);
        Class<?> clazz = Class.forName(interfaceName);
        T result = (T) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), new Class[]{clazz}, handler);
        return result;
    }

}
