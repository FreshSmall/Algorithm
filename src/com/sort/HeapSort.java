package com.sort;

/**
 * 堆排序
 */
public class HeapSort {

	public static void sort(int[] a){
		int N = a.length;

		//建立堆，使堆有序化
		for(int k = N/2;k>=1;k--){
			sink(a,k,N);
		}
		while(N>1){
			//交换第一位与最后一位的值，即将最大值后移，并重新制定数组的尾部指针
			exchange(a,1,N--);
			sink(a,1,N);
		}
	}

	private static void exchange(int[] a, int i, int i1) {
	}

	private static void sink(int[] a, int k, int n) {
		while(2*k<=n){
			int j = 2*k;
			//锁定子节点中的最大值

		}
	}
}
