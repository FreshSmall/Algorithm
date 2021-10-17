package com.network.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/5/28 9:48 上午
 */
public class NioSocketClient {

    private SocketChannel socketChannel;

    public void initClient(String host, int port) throws IOException {
        InetSocketAddress socketAddress = new InetSocketAddress(host, port);
        socketChannel = SocketChannel.open(socketAddress);
        socketChannel.configureBlocking(false);
    }

    public void sendAndRecv(String msg) throws IOException {
        byte[] buffer = msg.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        System.out.println("sending msg:" + msg);
        socketChannel.write(byteBuffer);
        byteBuffer.flip();
        socketChannel.read(byteBuffer);
        System.out.println("receiving msg:" + new String(byteBuffer.array(), 0, byteBuffer.position(), "utf-8"));
    }

    public static void main(String[] args) throws IOException {
        NioSocketClient client = new NioSocketClient();
        for (int i = 0; i < 10; i++) {
            client.initClient("localhost", 8083);
            client.sendAndRecv("hello");
        }

        /*ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] bytes = "这是一条普通消息！".getBytes();
        buffer.get(bytes);
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());*/
    }
}
