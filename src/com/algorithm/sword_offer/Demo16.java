package com.algorithm.sword_offer;

import com.algorithm.auxiliary.ListNode;

/**
 * 合并两个排序的链表
 */
public class Demo16 {


    public ListNode Merge(ListNode list1, ListNode list2) {
        ListNode p1 = null;
        ListNode cur = null;
        if(list1==null&&list2!=null){
            return list2;
        }
        if(list1!=null&&list2==null){
            return list1;
        }
        if(list1==null&&list2==null){
            return null;
        }

        while(list1!=null&&list2!=null){
            int val1 = list1.val;
            int val2 = list2.val;
            if(val1<val2){
                if(p1==null){
                    p1= new ListNode(val1);
                    cur = p1;
                }else{
                    p1.next = new ListNode(val1);
                    p1 = p1.next;
                }
                list1 = list1.next;
            }else{
                if(p1==null){
                    p1=new ListNode(val2);
                    cur = p1;
                }else{
                    p1.next = new ListNode(val2);
                    p1 = p1.next;
                }
                list2 = list2.next;
            }
        }

        if(list1!=null){
            p1.next = list1;
        }

        if(list2!=null){
            p1.next = list2;
        }

        return cur;
    }

    public static void main(String[] args) {
        ListNode t1 = new ListNode(1);
        ListNode t2 = new ListNode(3);
        ListNode t3 = new ListNode(5);

        ListNode t4 = new ListNode(2);
        ListNode t5 = new ListNode(4);
        ListNode t6 = new ListNode(6);
        t1.next = t2;
        t2.next = t3;

        t4.next = t5;
        t5.next = t6;

        Demo16 d = new Demo16();
        ListNode p1 = d.Merge(t1,t4);

        while(p1!=null){
            System.out.println(p1.val);
            p1 = p1.next;
        }
    }
}
