package com.algorithm.leetcode_china.tree;

import com.algorithm.auxiliary.TreeNode;

/**
 * 二叉树的直径
 */
public class Demo543 {

    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = root.left == null ? 0 : getDepth(root.left);
        int right = root.right == null ? 0 : getDepth(root.right);
        return left+right;
    }

    private int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }

    public static void main(String[] args) {
        Demo543 demo = new Demo543();
        TreeNode r1 = new TreeNode(4);
        TreeNode r2 = new TreeNode(-7);
        TreeNode r3 = new TreeNode(-3);
        TreeNode r4 = new TreeNode(-9);
        TreeNode r5 = new TreeNode(-3);
        TreeNode r6 = new TreeNode(9);
        TreeNode r7 = new TreeNode(-7);
        TreeNode r8 = new TreeNode(-4);
        TreeNode r9 = new TreeNode(6);
        TreeNode r10 = new TreeNode(-6);
        TreeNode r11 = new TreeNode(-6);
        TreeNode r12 = new TreeNode(0);
        TreeNode r13 = new TreeNode(6);
        TreeNode r14 = new TreeNode(5);
        TreeNode r15 = new TreeNode(9);
        TreeNode r16 = new TreeNode(-1);
        TreeNode r17 = new TreeNode(-4);
        TreeNode r18 = new TreeNode(-2);

        r1.left = r2;
        r1.right = r3;
        r3.left = r4;
        r3.right = r5;
        r4.left = r6;
        r4.right = r7;
        r5.left= r8;
        r6.left=r9;
        r7.right=r10;
        r8.left=r11;
        r10.left=r12;
        r10.right=r13;
        r11.left=r14;
        r12.right=r15;
        r14.left=r16;
        r4.right=r17;
        r16.right=r18;
        System.out.println(demo.diameterOfBinaryTree(r1));
    }
}
