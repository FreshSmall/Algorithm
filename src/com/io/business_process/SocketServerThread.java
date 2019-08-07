package com.io.business_process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketServerThread implements Runnable {
	private Socket socket;

	public SocketServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
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
			out.write("回访响应信息！".getBytes());
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
}
