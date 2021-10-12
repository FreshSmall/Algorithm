package com.netty.netty5.basic.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * Created by IntelliJ IDEA.
 * Netty时间服务器客户端
 *
 * @Author : yinchao
 * @create 2020/5/24 9:22 下午
 */
public class NettyTimeClient {

    public void connect(int port, String host) {
        //配置客户端NIO线程组
        EventLoopGroup group = new NioEventLoopGroup(1, new DefaultThreadFactory("server3", true));
        Bootstrap b = new Bootstrap();
        ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
        b.group(group).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
//                        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024)); //换行符解码器
//                        socketChannel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));//特殊字符解码器
//                        socketChannel.pipeline().addLast(new StringDecoder());
//                        socketChannel.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
//                        socketChannel.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                        socketChannel.pipeline().addLast(new TimeClientHandler());
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
        int port = 8080;
        for (int i = 0; i < 1; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new NettyTimeClient().connect(port, "localhost");
                }
            }).start();
        }
    }
}
