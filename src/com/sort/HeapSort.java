package com.sort;


/**
 * 堆排序
 */
public class HeapSort {

    public static void sort(int[] a) {
        // 构建最大堆
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            adjustHead(a, i, a.length);
        }

        //依次将堆顶元素（最大值）和末尾元素交换，重新构建最大堆
        for (int j = a.length - 1; j > 0; j--) {
            swap1(a, 0, j);
            adjustHead(a, 0, j);
        }

    }

    private static void swap1(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void adjustHead(int[] a, int i, int length) {
        int temp = a[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && a[k] < a[k + 1]) {
                k++;
            }
            if (a[k] > temp) {
                a[i] = a[k];
                i = k;
            } else {
                break;
            }
        }
        a[i] = temp;
    }


	public static void main(String[] args) {
		int[] a = new int[]{9,8,7,6,5,4,3,2,1};
		sort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

}
