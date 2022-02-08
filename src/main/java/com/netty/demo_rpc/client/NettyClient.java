package com.netty.demo_rpc.client;

import com.google.gson.Gson;
import com.netty.demo_rpc.domain.Request;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

public class NettyClient implements Runnable {

    private ChannelFuture channelFuture;
    private String ipHost;
    private int port;

    public NettyClient(String ipHost, int port) {
        this.ipHost = ipHost;
        this.port = port;
    }

    public void connect(String ipHost, int port) {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.AUTO_READ, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        // 解码转String，注意调整自己的编码格式GBK、UTF-8
                        channel.pipeline().addLast(new StringDecoder(Charset.forName("GBK")));
                        // 解码转String，注意调整自己的编码格式GBK、UTF-8
                        channel.pipeline().addLast(new StringEncoder(Charset.forName("GBK")));
                        // 在管道中添加我们自己的接收数据实现方法
                        channel.pipeline().addLast(new NettyClientHandler());
                    }
                });
        try {
            ChannelFuture f = b.connect(ipHost, port).sync();
            this.channelFuture = f;
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

    private String buildRequestString() {
        Request request = new Request();
        request.setMethodName("sayHello");
        request.setInterfaceName("com.netty.demo_rpc.impl.HelloServiceImpl");
        request.setArgs(new Object[]{"服务端你好"});
        return new Gson().toJson(request);
    }

    private Request buildRequest() {
        Request request = new Request();
        request.setMethodName("sayHello");
        request.setInterfaceName("com.netty.demo_rpc.impl.HelloServiceImpl");
        request.setArgs(new Object[]{"服务端你好"});
        return request;
    }

    public ChannelFuture getChannelFuture() {
        return channelFuture;
    }

    public void setChannelFuture(ChannelFuture channelFuture) {
        this.channelFuture = channelFuture;
    }

    @Override
    public void run() {
        connect(ipHost, port);
    }
}
