package com.io.bio_server;

import com.io.business_process.SocketServerThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioSocketServerThreadPool {

    //默认的端口号
    private static int DEFAULT_PORT = 8083;
    //线程池，懒汉式的单例
    private static ExecutorService executorService = Executors.newFixedThreadPool(60);

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            System.out.println("监听来自于" + DEFAULT_PORT + "端口的信息");
            serverSocket = new ServerSocket(DEFAULT_PORT);
            while (true) {
                //.accept()方法只能一个一个接受socket的情况，并且被阻塞的情况
                Socket socket = serverSocket.accept();
                //将业务处理交给线程池（之前逻辑是来一个连接就用一个线程处理）
                SocketServerThread socketServerThreadPool = new SocketServerThread(socket);
                executorService.execute(socketServerThreadPool);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


