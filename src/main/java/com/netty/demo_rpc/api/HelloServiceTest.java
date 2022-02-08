package com.netty.demo_rpc.api;

import com.netty.demo_rpc.client.NettyClient;
import com.netty.demo_rpc.domain.Request;
import com.netty.demo_rpc.proxy.JDKProxy;
import io.netty.channel.ChannelFuture;

public class HelloServiceTest {

    private ChannelFuture channelFuture;

    public void testSendRequest() throws Exception {
        NettyClient client = new NettyClient("localhost", 8083);
        new Thread(client).start();
        for (int i = 0; i < 100; i++) {
            if (null != channelFuture) break;
            Thread.sleep(500);
            channelFuture = client.getChannelFuture();
        }
        Request request = new Request();
        request.setMethodName("sayHello");
        request.setInterfaceName("com.netty.demo_rpc.impl.HelloServiceImpl");
        request.setApiName("com.netty.demo_rpc.api.HelloService");
        request.setArgs(new Object[]{"服务端你好"});
        HelloService helloService = JDKProxy.getProxy(request, channelFuture);
        System.out.println(helloService.sayHello("服务端你好"));
    }

    public static void main(String[] args) throws Exception {
        HelloServiceTest test = new HelloServiceTest();
        test.testSendRequest();
    }
}
