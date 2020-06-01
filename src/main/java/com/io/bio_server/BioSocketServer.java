package com.io.bio_server;

import com.io.business_process.SocketServerThread;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioSocketServer {

    //默认端口号
    private static int DEFAULT_PORT = 8083;

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            System.out.println("监听来自于" + DEFAULT_PORT + "端口的信息");
            serverSocket = new ServerSocket(DEFAULT_PORT);
            while (true) {
                //.accept()方法只能一个一个接受socket的情况，并且被阻塞的情况
                System.out.println("调用accept方法阻塞");
                Socket socket = serverSocket.accept();
                System.out.println("已有客户端连接进来");
                /*SocketServerThread socketServerThread = new SocketServerThread(socket);
                new Thread(socketServerThread).start();*/

                //下面我们收取信息
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = socket.getInputStream();
                    out = socket.getOutputStream();
                    int sourcePost = socket.getPort();
                    int maxLen = 1024;
                    byte[] contextBytes = new byte[maxLen];
                    //使用线程，无法解决read方法的阻塞问题
                    //也就是说read方法同样会被阻塞，直到操作系统有数据准备好
                    int realLen = in.read(contextBytes, 0, maxLen);
                    //读取信息
                    String message = new String(contextBytes, 0, realLen);
                    //打印如下信息
                    System.out.println("服务器收到来自于端口：" + sourcePost + "的信息：" + message);

                    //下面开始发送信息给客户端
                    out.write("回访响应信息！".getBytes("utf-8"));
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (in != null) {
                            in.close();
                        }
                        if (out != null) {
                            out.close();
                        }
                        if (socket != null) {
                            socket.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //这个wait不涉及具体的实验逻辑，只是为了保证守护线程在启动所有线程之后，进入等待状态
//		synchronized ()
    }
}

