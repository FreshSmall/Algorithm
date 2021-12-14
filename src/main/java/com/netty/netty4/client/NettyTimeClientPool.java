package com.netty.netty4.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.pool.AbstractChannelPoolMap;
import io.netty.channel.pool.ChannelPoolMap;
import io.netty.channel.pool.FixedChannelPool;
import io.netty.channel.pool.SimpleChannelPool;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;

import java.net.InetSocketAddress;

/**
 * Created by IntelliJ IDEA.
 * 采用pool池技术来进行客户端请求
 *
 * @Author : yinchao
 * @create 2020/5/27 9:49 上午
 */
public class NettyTimeClientPool {

    EventLoopGroup group = new NioEventLoopGroup();
    Bootstrap strap = new Bootstrap();
    InetSocketAddress addr = new InetSocketAddress("localhost", 8083);
    ChannelPoolMap<InetSocketAddress, SimpleChannelPool> poolMap;

    public void build() {
        strap.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true).option(ChannelOption.SO_KEEPALIVE, true);
        poolMap = new AbstractChannelPoolMap<InetSocketAddress, SimpleChannelPool>() {
            @Override
            protected SimpleChannelPool newPool(InetSocketAddress key) {
                return new FixedChannelPool(strap.remoteAddress(key), new NettyChannelPoolHandler(), 2);
            }
        };
    }

    public String send(String msg){
        build();
        for (int i = 0; i < 10; i++) {
            SimpleChannelPool pool = poolMap.get(addr);
            Future<Channel> f = pool.acquire();
            f.addListener(new FutureListener<Channel>() {
                @Override
                public void operationComplete(Future<Channel> f1) throws Exception {
                    if (f1.isSuccess()) {
                        Channel ch = f1.getNow();
                        ch.writeAndFlush("这是一条普通消息");
                        pool.release(ch);
                    }
                }
            });
        }
        return null;
    }

    public static void main(String[] args) {
        NettyTimeClientPool client = new NettyTimeClientPool();
        client.send("这是一条普通消息");

    }

}
