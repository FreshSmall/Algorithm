package com.io.business_process;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URLDecoder;
import java.util.concurrent.CountDownLatch;

/**
 * 一个clientRequestThread线程模拟一个客户端请求
 *
 */
public class ClientRequestThread implements Runnable{

	private CountDownLatch countDownLatch;

	/**
	 * 线程编号
	 */
	private Integer clientIndex;

	/**
	 * countDownLatch是java提供的同步计数器
	 * 当计数器值减为0时，所有受其影响而等待的线程将会被激活，这样保证并发请求的真实性
	 * @param countDownLatch
	 * @param clientIndex
	 */
	public ClientRequestThread(CountDownLatch countDownLatch,Integer clientIndex){
		this.countDownLatch = countDownLatch;
		this.clientIndex = clientIndex;
	}

	@Override
	public void run() {
		Socket socket = null;
		OutputStream clientRequest = null;
		InputStream clientResponse = null;
		try {
			socket = new Socket("localhost",8083);
			clientRequest = socket.getOutputStream();
			clientResponse = socket.getInputStream();

			//等待，知道socketClientDaemo完成所有线程的启动，然后所有线程一起发送请求
			this.countDownLatch.await();
			//发送请求信息
			clientRequest.write(("这是第："+this.clientIndex+"个客户端的请求。over").getBytes());
			clientRequest.flush();

			//在这里等待，知道服务器返回信息
			System.out.println("第"+this.clientIndex+"个客户端的请求发送完成，等待服务器返回信息");
			int maxLen = 1024;
			byte[] contextBytes = new byte[maxLen];
			int reaLen;
			String message = "";
			//程序执行到这里，会一直等待服务器返回信息(注意，前提是in和out都不能close，如果close就收不到服务器的反馈了)
			while((reaLen=clientResponse.read(contextBytes,0,maxLen))!=-1){
				message += new String(contextBytes,0,reaLen);
			}
			message = URLDecoder.decode(message,"UTF-8");
			System.out.println("第"+this.clientIndex+"个客户端接收到来自服务器的信息："+message);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (clientRequest != null) {
					clientRequest.close();
				}
				if (clientResponse!=null) {
					clientResponse.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
