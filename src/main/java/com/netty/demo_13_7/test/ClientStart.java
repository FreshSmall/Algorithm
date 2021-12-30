package com.netty.demo_13_7.test;

import com.alibaba.fastjson.JSON;
import com.netty.demo_13_7.client.NettyClient;
import com.netty.demo_13_7.future.SyncWrite;
import com.netty.demo_13_7.msg.Request;
import com.netty.demo_13_7.msg.Response;
import io.netty.channel.ChannelFuture;

public class ClientStart {

    private static ChannelFuture channelFuture;

    public static void main(String[] args) throws Exception {
        NettyClient client = new NettyClient();
        new Thread(client).start();

        while (true) {
            if (channelFuture == null) {
                channelFuture = client.getChannelFuture();
                Thread.sleep(300);
                continue;
            }

            Request request = new Request();
            request.setResult("测试客户端发送相关消息到服务端");
            SyncWrite s = new SyncWrite();
            Response response = s.writeAndSync(channelFuture.channel(), request, 100);
            System.out.println("调用结果：" + JSON.toJSON(response));
            Thread.sleep(1000);
        }

    }
}
