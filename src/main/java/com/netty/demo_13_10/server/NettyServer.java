package com.netty.demo_13_10.server;

import com.netty.demo_13_10.codec.ObjDecoder;
import com.netty.demo_13_10.codec.ObjEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NettyServer {

    public static void main(String[] args) {
        new NettyServer().bind(8083);
    }

    public void bind(int port) {
        EventLoopGroup parentLoop = new NioEventLoopGroup();
        EventLoopGroup childLoop = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(parentLoop, childLoop)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel channel) throws Exception {
                        ChannelPipeline pipeline = channel.pipeline();
                        pipeline.addLast(new ObjDecoder());
                        pipeline.addLast(new MsgDemo01Handler());
                        pipeline.addLast(new MsgDemo02Handler());
                        pipeline.addLast(new MsgDemo03Handler());
                        pipeline.addLast(new ObjEncoder());
                    }
                });
        try {
            ChannelFuture future = bootstrap.bind(port).sync();
            System.out.println(Thread.currentThread().getName() + ",服务器开始监听端口，等待客户端连接.......");
            //等待服务监听端口关闭
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            parentLoop.shutdownGracefully();
            childLoop.shutdownGracefully();
        }
    }
}
