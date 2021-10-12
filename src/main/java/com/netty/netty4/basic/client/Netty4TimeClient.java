package com.netty.netty4.basic.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * Created by IntelliJ IDEA.
 * Netty时间服务器客户端
 *
 * @Author : yinchao
 * @create 2020/5/24 9:22 下午
 */
public class Netty4TimeClient {

    public void connect(int port, String host) {
        //配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup(1, new DefaultThreadFactory("server3", true));
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new StringDecoder());
                        socketChannel.pipeline().addLast(new StringEncoder());
                        socketChannel.pipeline().addLast(new Netty4TimeClientHandler());
                    }
                });
        try {
            //发起异步连接操作
            ChannelFuture f = b.connect(host, port).sync();
            System.out.println(Thread.currentThread().getName() + ",客户端发起异步连接..........");
            //等待客户端链路关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅退出释放NIO线程组
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8083;
        for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new Netty4TimeClient().connect(port, "localhost");
                }
            }).start();
        }
    }
}
