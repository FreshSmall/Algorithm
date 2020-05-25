package com.io.bio_server;

import com.io.business_process.SocketServerThread;

import java.io.IOException;
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
                Socket socket = serverSocket.accept();
                SocketServerThread socketServerThread = new SocketServerThread(socket);
                new Thread(socketServerThread).start();
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

