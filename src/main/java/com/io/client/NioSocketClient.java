package com.io.client;

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
    }

    public void sendAndRecv(String msg) throws IOException {
        byte[] buffer = msg.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        System.out.println("sending msg:" + msg);
        socketChannel.write(byteBuffer);
        byteBuffer.clear();
        socketChannel.read(byteBuffer);
        System.out.println("receiving msg:" + msg);
        byteBuffer.clear();

        System.out.println("sending msg:" + msg + "again");
        byteBuffer = ByteBuffer.wrap((msg + ",again").getBytes());
        socketChannel.write(byteBuffer);
        byteBuffer.clear();
        socketChannel.read(byteBuffer);
        System.out.println("receiving msg:" + msg);
        byteBuffer.clear();
    }

    public void sendAndRecv1(String msg) throws IOException {
        byte[] buffer = msg.getBytes();
        byte[] buffer1 = (msg + "again").getBytes();
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        ByteBuffer byteBuffer1 = ByteBuffer.wrap(buffer1);
        socketChannel.write(byteBuffer);
        ByteBuffer byteBuffer3 = ByteBuffer.allocate(1000);
        socketChannel.read(byteBuffer3);


        System.out.println("receiving msg:" + new String(byteBuffer3.array(), 0, byteBuffer3.position(), "utf-8"));

        socketChannel.write(byteBuffer1);
        ByteBuffer byteBuffer4 = ByteBuffer.allocate(1000);

        System.out.println(socketChannel.read(byteBuffer4));
        System.out.println("receiving msg1:" + new String(byteBuffer4.array(), 0, byteBuffer3.position(), "utf-8"));


    }

    public static void main(String[] args) throws IOException {
        /*NioSocketClient client = new NioSocketClient();
        client.initClient("localhost", 8083);
        client.sendAndRecv1("这是一条普通消息");*/

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] bytes = "这是一条普通消息！".getBytes();
        buffer.get(bytes);
        buffer.flip();
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
    }
}
