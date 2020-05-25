package com.algorithm.leetcode;

import com.algorithm.basis.RandomListNode;

/**
 * 拷贝一个新的list链表
 */
public class Demo13 {

    public RandomListNode copyRandomList(RandomListNode head) {

        if (head == null) {
            return null;
        }

        RandomListNode pHead = null;
        RandomListNode pCurent = null;
        RandomListNode pNode = head;

        while (pNode != null) {
            RandomListNode node = new RandomListNode(pNode.label);
            if (pNode == head) {
                pHead = node;
                pCurent = node;

            }else {
                pHead.next = node;
                pHead = pHead.next;
            }
            pNode.random = pHead.random;
            pNode = pNode.next;
        }

        return pCurent;
    }

    public static void main(String[] args) {
        Demo13 dd = new Demo13();

        RandomListNode node = new RandomListNode(-1);
        RandomListNode node1 = new RandomListNode(-1);

        node.next = node1;

        RandomListNode pNode = dd.copyRandomList(node);
        while(pNode!=null){
            System.out.println(pNode.label);
            pNode = pNode.next;
        }



    }
}
