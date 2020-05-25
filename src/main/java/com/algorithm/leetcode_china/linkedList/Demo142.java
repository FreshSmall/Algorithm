package com.algorithm.leetcode_china.linkedList;

import com.algorithm.auxiliary.ListNode;

/**
 * 环形链表，找到环形链表的起点
 */
public class Demo142 {

    public ListNode detectCycle(ListNode head) {
        ListNode h1 = head;
        ListNode h2 = head;
        while(h1!=null&&h2!=null){

            h1 = h1.next;
            if(h1==null){
                return null;
            }
            h1 = h1.next;
            h2 = h2.next;

            if(h1==h2){
                break;
            }
        }

        h2 = head;
        while(h1!=null&&h2!=null){
            h1 = h1.next;
            h2 = h2.next;
            if(h2==h1){
                return h2;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Demo142 d = new Demo142();
        ListNode h1 = new ListNode(1);
        ListNode h2 = new ListNode(2);
        h1.next = h2;
        System.out.println(d.detectCycle(h1));
    }

}
