package com.algorithm.leetcode_china.linkedList;

import com.algorithm.auxiliary.ListNode;
import com.algorithm.leetcode.Demo1;

/**
 * 回文链表
 */
public class Demo234 {

    public boolean isPalindrome(ListNode head) {
        ListNode p1 = head;
        int count = 0;
        while (p1 != null) {
            p1 = p1.next;
            count++;
        }
        int temp = count / 2;
        ListNode pre = null, cur = head, nex = null;
        for (int i = 0; i < temp; i++) {
            nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        if (count % 2 == 1) {
            cur = cur.next;
        }

        while (cur != null && pre != null) {
            if (cur.val != pre.val) {
                return false;
            }
            cur = cur.next;
            pre = pre.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Demo234 d = new Demo234();
        ListNode h1 = new ListNode(0);
        ListNode h2 = new ListNode(1);
        ListNode h3 = new ListNode(0);
        h1.next = h2;
        h2.next = h3;
        System.out.println(d.isPalindrome(h1));
    }
}
