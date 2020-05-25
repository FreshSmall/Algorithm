package com.algorithm.sword_offer;

import com.algorithm.auxiliary.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 层序打印二叉树
 */
public class Demo60 {

    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if(pRoot==null){
            return arrayLists;
        }
        LinkedList<TreeNode> l1 = new LinkedList<TreeNode>();//存放奇数行树节点
        l1.add(pRoot);
        int nexeLevel = 0;
        int toBePrinted =1;
        ArrayList<Integer> arrayList = new ArrayList<>();
        while(!l1.isEmpty()){
            TreeNode node = l1.pop();
//            System.out.print(node.val);
            arrayList.add(node.val);
            if(node.left!=null){
                l1.add(node.left);
                nexeLevel++;
            }
            if(node.right!=null){
                l1.add(node.right);
                nexeLevel++;
            }
            --toBePrinted;
            if(toBePrinted==0){
                toBePrinted=nexeLevel;
                nexeLevel=0;
                System.out.println("");
                arrayLists.add(arrayList);
                arrayList = new ArrayList<>();
            }
        }
        return arrayLists;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t2.right=t5;
        t3.left=t6;
        t3.right=t7;
        Demo60 d = new Demo60();
        d.Print(t1);
    }
}
