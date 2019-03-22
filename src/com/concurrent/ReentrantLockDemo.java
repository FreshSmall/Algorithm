package com.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {

	private static long start;

	private static CyclicBarrier cycliBarrier = new CyclicBarrier(10, new Runnable() {
		@Override
		public void run() {
			System.out.println("线程执行完毕，开始查看修改值："+i);

			System.out.println(System.currentTimeMillis()-start+"ms");

		}
	});


	private static ReentrantLock lock = new ReentrantLock();

	private static int i = 0;

	public static void increntValue() {
		lock.lock();
		i++;
		lock.unlock();
	}

	static class IncrementVauleThread implements Runnable {

		@Override
		public void run() {

			for (int i = 0; i < 10000; i++) {
				increntValue();
			}

			try {
				cycliBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) {

		start = System.currentTimeMillis();

		for (int i = 0; i < 10; i++) {
			new Thread(new IncrementVauleThread()).start();
		}

	}
}
