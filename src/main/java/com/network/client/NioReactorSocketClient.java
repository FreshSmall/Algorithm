package com.network.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/6/1 4:20 下午
 */
public class NioReactorSocketClient {
    private SocketChannel socketChannel;
    private boolean flag;

    public void initClient(String host, int port) throws Exception {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress socketAddress = new InetSocketAddress(host, port);
        flag = socketChannel.connect(socketAddress);

    }

    public void sendMsg() throws Exception {
        Selector selector = Selector.open();
        if (flag) {
            SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ, "测试秘钥");
            System.out.println("连接成功开始发送数据");
            System.out.println(key.attachment());
            write(socketChannel);
        } else {
            SelectionKey key = socketChannel.register(selector, SelectionKey.OP_CONNECT, "测试秘钥");
            System.out.println(key.attachment());
        }
        while (true) {
            try {
                selector.select(1000);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = keys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                SocketChannel channel = (SocketChannel) key.channel();
                if (key.isConnectable()) {
                    System.out.println("客户端已经连接完毕");
                    if (channel.finishConnect()) {
                        socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                        write(channel);
                    }
                } else if (key.isReadable()) {
                    System.out.println("通道读事件准备完毕");
                    read(channel);
                } else if (key.isWritable()) {
                    System.out.println("通道写事件准备完毕");
//                    write(channel);
                }
                iterator.remove();
            }
        }
    }

    private void read(SocketChannel channel) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        channel.read(byteBuffer);
        System.out.println("receiving msg:" + new String(byteBuffer.array(), 0, byteBuffer.position(), "utf-8"));
    }

    private void write(SocketChannel socketChannel) throws IOException {
        String msg = "发送数据到服务端";
        byte[] buffer = msg.getBytes();
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        System.out.println("sending msg:" + msg);
        socketChannel.write(byteBuffer);
        byteBuffer.flip();
    }

    public static void main(String[] args) throws Exception {
        NioReactorSocketClient client = new NioReactorSocketClient();
        client.initClient("localhost", 8083);
        client.sendMsg();
    }
}
