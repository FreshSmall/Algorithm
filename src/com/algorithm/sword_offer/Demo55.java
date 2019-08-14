package com.algorithm.sword_offer;

import com.algorithm.auxiliary.ListNode;

/**
 * 链表中环的入口节点
 */
public class Demo55 {

   /* public ListNode EntryNodeOfLoop(ListNode pHead) {
        if(pHead==null){
            return null;
        }

        ListNode p1 = pHead;
        ListNode p2 = pHead;

        while(p2!=null){
            p2=p2.next;
            if(p2!=null){
                p2=p2.next;
            }
            p1=p1.next;
            if(p2==p1){
                p1 = pHead;

                while(p2!=p1){
                    p2 = p2.next;
                    p1 = p1.next;
                }
                if(p1 == p2){
                    return p1;
                }
            }
        }
        return null;
    }
*/

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head ;
        ListNode quick = head;
        if(head==null||head.next==null){
            return null;
        }
        while(quick.next.next!=null&&quick!=slow){
            quick = quick.next.next;
            slow = slow.next;
        }
        if(quick==null){
            return null;
        }
        System.out.println(quick.val);
        slow = head;
        while(quick != slow){
            quick = quick.next;
            slow = slow.next;
        }
        return quick;

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(5);
        ListNode l6 = new ListNode(6);
        l1.next=l2;
        l2.next=l3;
        l3.next=l4;
        l4.next=l5;
        l5.next=l6;
        l6.next=l3;
        Demo55 d = new Demo55();
//        d.EntryNodeOfLoop(listNode);
        d.detectCycle(l1);
    }
}
