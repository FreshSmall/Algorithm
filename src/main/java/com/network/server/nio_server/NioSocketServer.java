package com.network.server.nio_server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/6/1 2:07 下午
 */
public class NioSocketServer {

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8083));
        serverSocketChannel.configureBlocking(false);
        while (true) {
            SocketChannel client = serverSocketChannel.accept();
            if (client == null) {
//                System.out.println("未连接客户端....");
            } else {
                new Thread(new SocketChannelThread(client)).start();
            }
            Thread.sleep(1000);
        }

    }
}

class SocketChannelThread implements Runnable {

    private SocketChannel client;

    public SocketChannelThread(SocketChannel client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            client.configureBlocking(false);
            System.out.println("客户端的数据已经连接，port:" + ((InetSocketAddress) client.getRemoteAddress()).getPort());
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
                int readIndex = client.read(byteBuffer);
                if (readIndex == 0) {
//                    System.out.println("连接的客户端传得数据为空");
                } else if (readIndex > 0) {
                    byteBuffer.flip();
                    System.out.println("receive msg body:" + new String(byteBuffer.array(), 0, byteBuffer.limit(), "utf-8"));
                    client.write(byteBuffer);
                } else {
                    System.out.println("客户端已经断开");
                    client.close();
                }
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
