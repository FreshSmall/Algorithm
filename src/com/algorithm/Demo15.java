package com.algorithm;

import com.algorithm.auxiliary.ListNode;

/**
 * 反转链表
 */
public class Demo15 {


    public ListNode ReverseList(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        while(cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static void main(String[] args) {
        ListNode t1 = new ListNode(1);
        ListNode t2 = new ListNode(2);
        ListNode t3 = new ListNode(3);
        ListNode t4 = new ListNode(4);
        ListNode t5 = new ListNode(5);
        ListNode t6 = new ListNode(6);
        ListNode t7 = new ListNode(7);
        t1.next = t2;
        t2.next = t3;
        t3.next = t4;
        t4.next = t5;
        t5.next = t6;
        t6.next = t7;

        Demo15 d = new Demo15();
        ListNode p1 = d.ReverseList(t1);
        while(p1!=null){
            System.out.println(p1.val);
            p1 = p1.next;
        }
    }
}
