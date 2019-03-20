package com.leetcode;

import com.algorithm.auxiliary.TreeNode;

/**
 * 查找树的最少路径
 */
public class Demo1 {

    public int run(TreeNode root) {
        return 0;
    }

    public int treeDepth(TreeNode root){
        if(root==null){
            return 0;
        }else{
            return treeDepth(root.left)<treeDepth(root.right)?treeDepth(root.left)+1:treeDepth(root.right)+1;
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(7);
        TreeNode t4 = new TreeNode(2);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(8);
        t1.left=t2;
//        t1.right=t3;
//        t2.left=t4;
//        t2.right=t5;
//        t3.left=t6;
//        t3.right=t7;
        Demo1  d = new Demo1();
        int t = d.treeDepth(t1);
        System.out.println(t);

    }
}
