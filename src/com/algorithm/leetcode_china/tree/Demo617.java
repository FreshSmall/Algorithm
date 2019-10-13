package com.algorithm.leetcode_china.tree;

import com.algorithm.auxiliary.TreeNode;

/**
 * 合并二叉树
 */
public class Demo617 {


    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        TreeNode new_node = null;
        TreeNode l = null;
        TreeNode r = null;
        if (t1 == null && t2 == null) {
            return new_node;
        } else if (t1 != null && t2 == null) {
            new_node = new TreeNode(t1.val);
            l = mergeTrees(t1.left,null);
            r = mergeTrees(t1.right,null);
        } else if (t1 == null && t2 != null) {
            new_node = new TreeNode(t2.val);
            l = mergeTrees(null, t2.left);
            r = mergeTrees(null, t2.right);
        }else{
            new_node = new TreeNode(t1.val+t2.val);
            l = mergeTrees(t1.left,t2.left);
            r = mergeTrees(t1.right,t2.right);
        }
        new_node.left = l;
        new_node.right=r;

        return new_node;

    }


    public static void main(String[] args) {
        Demo617 demo = new Demo617();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t2.left = t3;
        t2.right = t4;
        t3.left = t5;
        t1 = demo.mergeTrees(t1, t2);
        System.out.println(t1.val);
    }
}
