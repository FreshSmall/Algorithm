package com.frame_work;

import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class QueueDemo {

	/**
	 * 属于队列范畴
	 */
	public void testLinkList() {
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		Iterator<Integer> iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}


	/**
	 * 测试最小堆队列
	 */
	public void testPriorityQueue() {
		PriorityQueue<Integer> pq = new PriorityQueue<>(12, new Comparator<Integer>() {
			@Override
			public int compare(Integer a, Integer b) {
				return a - b;
			}
		});
		pq.add(1);
		pq.add(2);
		pq.add(3);
		System.out.println(pq.poll());
		System.out.println(pq.poll());
		System.out.println(pq.poll());
	}

	/**
	 * 阻塞队列
	 *
	 * @throws InterruptedException
	 */
	public void testArrayBlockQueue() throws InterruptedException {
		ArrayBlockingQueue<Integer> abq = new ArrayBlockingQueue<Integer>(12);
		abq.add(12);
		abq.offer(12, 10, TimeUnit.SECONDS);
	}


	public void testSynchronousQueue() throws Exception {
		SynchronousQueue<Integer> sq = new SynchronousQueue<Integer>(false);
		new Thread(()->{
			while(sq.isEmpty()){
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(sq.poll());
			}
		}).start();
		new Thread(() -> {
			try {
				sq.put(1);
				System.out.println("线程1放入元素完成");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();



	}


	public void testConcurrentLinkedQueue() {
		ConcurrentLinkedQueue<Integer> clq = new ConcurrentLinkedQueue<>();
		clq.add(1);
		clq.add(2);
	}

	public static void main(String[] args) throws Exception {
		QueueDemo queueDemo = new QueueDemo();
		queueDemo.testSynchronousQueue();
	}
}
