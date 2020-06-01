package com.io.netty5.protobuf.server;

import com.io.netty5.protobuf.SubscribeReqProto;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * Created by IntelliJ IDEA.
 * Protobuf版本的图书订购服务端
 * @Author : yinchao
 * @create 2020/5/26 1:57 下午
 */
public class SubReqServer {

    public void bind(int port) {
        //配置服务端的NIO线程组
        EventLoopGroup boosGroup = new NioEventLoopGroup(1, new DefaultThreadFactory("server1", true));
        EventLoopGroup workerGroup = new NioEventLoopGroup(2, new DefaultThreadFactory("server2", true));

        ServerBootstrap b = new ServerBootstrap();
        b.group(boosGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childHandler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        System.out.println(Thread.currentThread().getName()+",服务器初始化通道.....");
                        socketChannel.pipeline().addLast(new ProtobufVarint32FrameDecoder());
                        socketChannel.pipeline().addLast(new ProtobufDecoder(SubscribeReqProto.SubscribeReq.getDefaultInstance()));
                        socketChannel.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
                        socketChannel.pipeline().addLast(new ProtobufEncoder());
                        socketChannel.pipeline().addLast(new SubReqServerHandler());
                    }
                });

        try {
            //绑定端口等待同步成功
            ChannelFuture f = b.bind(port).sync();
            System.out.println(Thread.currentThread().getName()+",服务器开始监听端口，等待客户端连接.......");
            //等待服务监听端口关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //优雅退出，释放线程资源
            boosGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 8080;
        new SubReqServer().bind(port);
    }
}
