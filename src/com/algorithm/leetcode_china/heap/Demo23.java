package com.algorithm.leetcode_china.heap;

import com.algorithm.auxiliary.ListNode;

import java.util.Arrays;

/**
 * 合并k个链表
 */
public class Demo23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int begin, int end) {
        if (begin == end) {
            return lists[begin];
        }
        int midle = (begin + end) / 2;
        ListNode l1 = merge(lists, begin, midle);
        ListNode l2 = merge(lists, midle + 1, end);
        return mergeTwoList(l1, l2);
    }

    private ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (l1==null) {
            return l2;
        }
        if (l2==null) {
            return l1;
        }
        if(l1.val<l2.val){
            l1.next = mergeTwoList(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoList(l1,l2.next);
            return l2;
        }
    }


    public static void main(String[] args) {

    }
}
