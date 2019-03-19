package com.algorithm;

import com.algorithm.aux.ListNode;

/**
 * 获取两个链表的公共节点
 * 首先获取两个链表的长短，然后让长的链表先遍历掉之间的差值，然后同时遍历，第一个相同的节点就是公共节点
 */
public class Demo36 {

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        int h1 = getListLength(pHead1);
        int h2 = getListLength(pHead2);
        int length = h1 -h2;
        ListNode pLong = pHead1;
        ListNode pShort = pHead2;
        if(length<0){
            pShort = pHead1;
            pLong = pHead2;
            length = h2-h1;
        }

        for(int i=0;i<length;i++){
            pLong = pLong.next;
        }

        while(pLong!=null&&pShort!=null&&pLong!=pShort){
            pLong = pLong.next;
            pShort = pShort.next;
        }

        return pLong;
    }

    private int getListLength(ListNode pHead2) {
        int count = 0;
        ListNode pHead = pHead2;
        while (pHead!=null){
            count ++;
            pHead = pHead.next;
        }
        return count;
    }
}
