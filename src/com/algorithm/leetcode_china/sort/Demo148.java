package com.algorithm.leetcode_china.sort;

import com.algorithm.auxiliary.ListNode;

/**
 * 链表排序，将一个链表进行排序
 */
public class Demo148 {
    public ListNode sortList(ListNode head) {
        if (head == null||head.next==null) {
            return head;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (slow != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode temp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(temp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left == null ? left : right;
        return res.next;
    }

    public static void main(String[] args) {
        Demo148 d = new Demo148();
        ListNode h1 = new ListNode(1);
        ListNode h2 = new ListNode(3);
        ListNode h3 = new ListNode(6);
        ListNode h4 = new ListNode(5);
        ListNode h5 = new ListNode(2);
        h1.next = h2;
        h2.next = h3;
        h3.next = h4;
        h4.next = h5;
        System.out.println(d.sortList(h1));
    }
}
