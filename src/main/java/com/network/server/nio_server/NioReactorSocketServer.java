package com.network.server.nio_server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class NioReactorSocketServer {

    public static void main(String[] args) {
        ServerSocketChannel serverChannel = null;
        ServerSocket serverSocket = null;
        try {
            serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);

            Selector selector = Selector.open();
            //注意：服务器通道只能注册SelectionKey.OP_ACCEPT事件
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            serverSocket = serverChannel.socket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(8083));

            while (true) {
                //如果条件成立，说明本次询问selector，并没有获取到任务准备好的、感兴趣的事件
                //java程序对多路复用IO的支持也包括了阻塞模式和非阻塞模式
                if (selector.select(100) == 0) {
                    continue;
                }
                //这里就是本次询问操作系统，所获取的"所关心的事件"的事件类型（每一个通道都是独立的）
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey readyKey = iterator.next();
                    //这个已经处理的readKey一定要移除，如果不移除，就会一直存在在selector.selectKeys集合中
                    //待到下一次selector.select()>0时，这个selectKey又会被处理一次

                    if (readyKey.isAcceptable()) {
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) readyKey
                            .channel();
                        SocketChannel clientChannel = serverSocketChannel.accept();
                        clientChannel.configureBlocking(false);
                        clientChannel.register(readyKey.selector(), SelectionKey.OP_READ);
                    } else if (readyKey.isReadable()) {
                        SocketChannel clientChannel = (SocketChannel) readyKey.channel();
                        ByteBuffer readBuffer = ByteBuffer.allocate(10);
                        int readBytes = clientChannel.read(readBuffer);
                        if (readBytes == -1) {
                            System.out.println("closed.......");
                            clientChannel.close();
                        } else if (readBytes > 0) {
                            String s = new String(readBuffer.array());
                            System.out.println("Client said: " + s);
                            if (s.trim().equalsIgnoreCase("Hello")) {
                                // attachment is content used to write
                                readyKey.interestOps(SelectionKey.OP_WRITE);
                                readyKey.attach("Welcome maizi !!!");
                            }
                        }
                    } else if (readyKey.isValid() && readyKey.isWritable()) {
                        SocketChannel clientChannel = (SocketChannel) readyKey.channel();
                        String content = (String) readyKey.attachment();
                        // write content to socket channel
                        clientChannel.write(ByteBuffer.wrap(content.getBytes()));
                        readyKey.interestOps(SelectionKey.OP_READ);
                    }
                    iterator.remove();

                   /* if (readyKey.isValid() && readyKey.isAcceptable()) {
                        System.out.println("=======channel通道已经准备好=======");
                        *//**
                     * 当server socket channel通道已经准备好，就可以从server socket channel中获取socketchannel了
                     * 拿到socket channel后，要做的事情就是马上到selector注册这个socket channel感兴趣的事情
                     * 否则无法监听这个socket channel到达的数据
                     *//*
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectableChannel;
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        registerSocketChannel(socketChannel, selector);
                    } else if (readyKey.isValid() && readyKey.isConnectable()) {
                        System.out.println("============socket channel建立连接===========");
                    } else if (readyKey.isValid() && readyKey.isReadable()) {
                        System.out
                            .println("============socket channel数据准备好，可以去读取======================");
                        readSocketChannel(readyKey);
                    }*/
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 在server socket channel接收到/准备好一个新的TCP连接后 就会向程序返回一个新的socketChannel 但是这个新的socket
     * channle并没有在selector"选择器/迭代器"中注册 所以程序还没法通过selector通知这个socket channel的事件 于是我们拿到新的socket
     * channel后，要做的第一个事情就是到selector"选择器/迭代器"中注册这个
     *
     * @param socketChannel
     * @param selector
     */
    private static void registerSocketChannel(SocketChannel socketChannel, Selector selector)
        throws IOException {
        socketChannel.configureBlocking(false);
        //socket通道可以且只可以注册三种事件SelectionKey.OP_READ|SelectionKey.OP_WRITE|Selectionkey.OP_CONNECT
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(2048));
    }

    /**
     * 这个方法用于读取从客户端传来的信息 并且观察从客户端过来的socket channel在经过多次传输后，是否完成传输 如果传输完成，则返回一个true标记
     */
    private static void readSocketChannel(SelectionKey readyKey) throws IOException {
        SocketChannel clientSocketChannel = (SocketChannel) readyKey.channel();
        //获取客户端使用的端口
        InetSocketAddress sourceSocketAddress = (InetSocketAddress) clientSocketChannel
            .getRemoteAddress();
        int resourcePort = sourceSocketAddress.getPort();
        //拿到这个socket channel使用的缓冲区，准备读取数据
        //在后文，将详细讲解缓存区的用法概念，实际上重要的就是三个元素，capacity,position和limit
        ByteBuffer contextBuffer = (ByteBuffer) readyKey.attachment();
        //将通道的数据写入到缓存区，注意是写入到缓存区
        //由于之前设置了ByteBuffer的大小为2048 byte,所以可以存在写入不完的情况
        //暂时理解为一次接受可以完后(后面调整代码)
        int readLen = -1;
        try {
            readLen = clientSocketChannel.read(contextBuffer);
        } catch (Exception e) {
            //这里抛出了异常，一般就是客户端因为某种原因终止了，所以关闭了channel就行了
            e.printStackTrace();
            clientSocketChannel.close();
            return;
        }

        //如果缓冲区没有任何数据（实际上应该不可能，否则就不会触发OP_READ事件了）
        if (readLen == -1) {
            System.out.println("========缓存区没有数据=======");
            return;
        }

        //将缓存区从写状态切换为读状态（实际上这个方式读写模式互换）
        //这是java nio框架中的这个socket channel的写请求将全部等待
        contextBuffer.flip();
        //注意中文乱码的问题，最好使用URLDecoder/URLEncoder，进行编解码
        //当然java nio框架本身也提供编码解码方式
        byte[] messageBytes = contextBuffer.array();
        String messageEncode = new String(messageBytes, "UTF-8");
        String message = URLDecoder.decode(messageEncode, "UTF-8");

        //如果收到了"over"关键字，才会清空buffer，并回发数据
        //否则不清空缓存，还要还原buffer的"写状态"
        if (message.indexOf("over") != -1) {
            //清空已经读取的缓存，并重新切换为写状态（需要注意clear和capacity两个方法的区别）
            contextBuffer.clear();
            System.out.println("端口：" + resourcePort + "客户端发来的信息======message：" + message);

            //======================================
            //      接受完成之后，可以正式处理业务
            //======================================

            //回发数据，并关闭channel
            ByteBuffer sendBuffer = ByteBuffer
                .wrap(URLEncoder.encode("你好，客户端，这里是服务器的返回数据", "UTF-8").getBytes());
            clientSocketChannel.write(sendBuffer);
            clientSocketChannel.close();
        } else {
            System.out.println("端口：" + resourcePort + "客户端信息还未接受完，继续接受=========message:" + message);
            //这是，limit和capacity的值一致，position的位置是reaLen的位置
            contextBuffer.position(readLen);
            contextBuffer.limit(contextBuffer.capacity());
        }


    }


}
