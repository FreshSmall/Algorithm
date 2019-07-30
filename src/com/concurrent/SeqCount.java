package com.concurrent;


/**
 * ThreadLocal使用示例
 */
public class SeqCount {

	private static ThreadLocal<Integer> seqCount = new ThreadLocal<Integer>(){
		public Integer initialValue(){
			return 0;
		}
	};


	public int nextSeq(){
		seqCount.set(seqCount.get()+1);
		return seqCount.get();
	}


	static class SeqThread extends Thread{
		private SeqCount seqCount;
		public SeqThread(SeqCount seqCount){
			this.seqCount = seqCount;
		}
		@Override
		public void run(){
			for(int i=0;i<3;i++){
				System.out.println("threadName:"+Thread.currentThread().getName()+"--i:"+i+",count:"+seqCount.nextSeq());
			}
		}
	}
	public static void main(String[] args) {
		SeqCount seqCount = new SeqCount();
		SeqThread t1 = new SeqThread(seqCount);
		SeqThread t2 = new SeqThread(seqCount);
		SeqThread t3 = new SeqThread(seqCount);
		SeqThread t4 = new SeqThread(seqCount);

		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}
