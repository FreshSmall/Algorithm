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

public class NettyClient {


    public static void main(String[] args) {
        new NettyClient().connect("localhost", 8083);
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
            f.channel().writeAndFlush(buildRequest());
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }

    private String buildRequest() {
        Request request = new Request();
        request.setMethodName("sayHello");
        request.setInterfaceName("com.netty.demo_rpc.impl.HelloServiceImpl");
        request.setArgs(new Object[]{"服务端你好"});
        return new Gson().toJson(request);
    }
}
