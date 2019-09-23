package com.algorithm.leetcode_china.linkedList;

import com.algorithm.auxiliary.ListNode;

/**
 * 删除链表的倒数第n个节点
 */
public class Demo19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode h1 = head;
        ListNode h2 = head;
        ListNode hpre = null;
        ListNode h = hpre;
        int count = 0;
        while (count < n) {
            h1 = h1.next;
            count++;
        }

        if (h1 == null) {
            return null;
        }

        while (h1 != null) {
            h1 = h1.next;
            if(hpre == null){
                hpre = h2;
                h = hpre;
                h2 = h2.next;
            }else{
                hpre.next = h2;
                hpre = h2;
                h2 = h2.next;
            }
        }

        ListNode temp = hpre.next;
        hpre.next = temp.next;
        return h;
    }

    public static void main(String[] args) {

        Demo19 d = new Demo19();

        ListNode h1 = new ListNode(1);
        ListNode h2 = new ListNode(2);
        ListNode h3 = new ListNode(3);
        ListNode h4 = new ListNode(4);
        ListNode h5 = new ListNode(5);
        h1.next = h2;
        /*h2.next = h3;
        h3.next = h4;
        h4.next = h5;*/

        ListNode h = d.removeNthFromEnd(h1, 1);
        System.out.println(h);
    }
}
