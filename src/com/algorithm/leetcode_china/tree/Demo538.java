package com.algorithm.leetcode_china.tree;


import com.algorithm.auxiliary.TreeNode;

/**
 * 把二叉搜索树转换为累加树
 */
public class Demo538 {

    private int add =0;
    public TreeNode convertBST(TreeNode root) {
        if (root==null) {
            return root;
        }
        convertBST(root.right);
        root.val+=add;
        add = root.val;
        convertBST(root.left);
        return root;
    }

    public static void main(String[] args) {
        Demo538 demo = new Demo538();
        TreeNode r1 = new TreeNode(6);
        TreeNode r2 = new TreeNode(4);
        TreeNode r3 = new TreeNode(8);
        TreeNode r4 = new TreeNode(3);
        TreeNode r5 = new TreeNode(5);
        TreeNode r6 = new TreeNode(7);
        TreeNode r7 = new TreeNode(9);
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.left = r6;
        r3.right = r7;

        TreeNode temp = demo.convertBST(r1);

        System.out.println(temp);
    }
}
