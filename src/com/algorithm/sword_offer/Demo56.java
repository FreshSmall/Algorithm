package com.algorithm.sword_offer;

import com.algorithm.auxiliary.ListNode;

/**
 * 删除链表中重复的节点
 */
public class Demo56 {

    public ListNode deleteDuplication(ListNode pHead) {

        if (pHead == null || pHead.next == null)
            return pHead;
        ListNode pre = null;
        ListNode cur = pHead;
        ListNode pp = null;
        while(cur!=null){
            int temp = cur.val;
            if(cur.next!=null&&cur.val ==cur.next.val){
                while(cur!=null&&(cur.val == temp)){
                    cur = cur.next;
                }
            }else{
                if(pre == null){
                    pre = cur;
                    pp = pre;
                }else {
                    pre.next = cur;
                    pre = pre.next;
                }
                cur = cur.next;
            }

            if(cur==null){
                if(pre == null){
                    pre = cur;
                    pp = pre;
                }else {
                    pre.next = cur;
                    pre = pre.next;
                }
            }

        }
        return pp;
    }


    public ListNode deleteDuplication1(ListNode pHead) {
        if(pHead==null||pHead.next==null)
            return pHead;

        ListNode ppre=null;
        ListNode pcur=pHead;//pcur始终指向排序链表每个数字第一次出现时候的位置。
        ListNode pnext=null;

        while(pcur!=null){
            pnext=pcur.next;
            if(pnext!=null&&(pcur.val==pnext.val)){
                int temp=pcur.val;
                while(pcur!=null&&pcur.val==temp){
                    pcur=pcur.next;
                }
                if(ppre==null){
                    pHead=pcur;
                }else{
                    ppre.next=pcur;
                }
            } else{//若pcur和pnext不是重复数字，更新ppre
                ppre=pcur;
                pcur=pnext;
            }
        }
        return pHead;
    }

    public static void main(String[] args) {
        ListNode t1 = new ListNode(1);
        ListNode t2 = new ListNode(2);
        ListNode t3 = new ListNode(2);
        ListNode t4 = new ListNode(3);
        ListNode t5 = new ListNode(3);
        ListNode t6 = new ListNode(4);
        ListNode t7 = new ListNode(5);
        t1.next = t2;
        t2.next = t3;
        t3.next = t4;
        t4.next = t5;
        t5.next = t6;
        t6.next = t7;

        Demo56 d = new Demo56();
        ListNode pp = d.deleteDuplication1(t1);

        while (pp != null) {
            System.out.println(pp.val);
            pp = pp.next;
        }

    }
}
