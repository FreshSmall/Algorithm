package com.algorithm;

import com.algorithm.auxiliary.TreeNode;

import java.util.Stack;

/**
 * 重新创建二叉树
 * 通过前序和中序
 */
public class Demo4 {

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return reConstruct(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }


    private TreeNode reConstruct(int[] pre, int preBegin, int preEnd, int[] in, int inBegin, int inEnd) {
        if (preBegin > preEnd || inBegin > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preBegin]);
        for (int i = inBegin; i <= inEnd; i++) {
            if (in[i] == pre[preBegin]) {
                root.left = reConstruct(pre, preBegin + 1, preBegin+i-inBegin, in, inBegin, i - 1);
                root.right = reConstruct(pre, preBegin+i+1-inBegin, preEnd, in, i + 1, inEnd);
            }
        }
        return root;
    }

    public static void main(String[] args) {
//        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
//        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        int[] pre = {1,3, 5, 6, 8};
        int[] in = {1, 5, 3, 8, 6};

        Demo4 demo = new Demo4();

        TreeNode head = demo.reConstructBinaryTree(pre, in);
        System.out.println("ceshi");


        Stack<Integer> stack = new Stack<Integer>();


    }

}
