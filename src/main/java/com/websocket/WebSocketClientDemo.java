package com.websocket;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.enums.ReadyState;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: yinchao
 * @ClassName: WebSocketClient
 * @Description: webSocket 客户端连接
 * @team wuhan operational dev.
 * @date: 2022/10/13 10:27
 */
public class WebSocketClientDemo extends WebSocketClient {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 500, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(20000));


    public WebSocketClientDemo(URI serverUri) {
        super(serverUri);
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        System.out.println("websocket is open");
    }

    @Override
    public void onMessage(String s) {
        System.out.println("接收到服务端数据:" + s);
    }

    @Override
    public void onClose(int i, String s, boolean b) {
        System.out.println("websocket is close");
    }

    @Override
    public void onError(Exception e) {
        System.out.println("websocket on error");
    }

    public static void main(String[] args) {
        // 测试多ws连接
        for (int i = 0; i < 2; i++) {
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1; j++) {
                        String url = "ws://localhost:12501/call?token=6500341a28340a528dfed4fe8ea12c74&cno=3030&serviceId=10030271&businessId=wstest&wsConnectionId=10030271.4581.16656259467231133"+ UUID.randomUUID();
                        WebSocketClientDemo demo = new WebSocketClientDemo(URI.create(url));
                        demo.connect();
                        while (!demo.getReadyState().equals(ReadyState.OPEN)) {

                        }
                        System.out.println("数据发送完毕");
                    }
                }
            });
        }
    }


}
