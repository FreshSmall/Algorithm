package com.algorithm.leetcode_china.linkedList;

import com.algorithm.auxiliary.ListNode;

/**
 * 链表反转
 */
public class Demo206 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;
        ListNode pre = null, nex = null;
        while (cur != null) {
            nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }
}
