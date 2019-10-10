package com.algorithm.leetcode_china.tree;

import com.algorithm.auxiliary.TreeNode;

import java.util.LinkedList;

/**
 * 翻转二叉树
 */
public class Demo226 {

    public TreeNode invertTree(TreeNode root) {
        if(root==null){
            return null;
        }
        TreeNode right = invertTree(root.right);
        TreeNode left = invertTree(root.left);
        root.left = right;
        root.right = left;
        return root;
    }

    public TreeNode invertTree1(TreeNode root){
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode current = list.poll();
            if (current.left!=null) {
                list.add(current.left);
            }

            if (current.right!=null) {
                list.add(current.right);
            }
            TreeNode temp = current.left;
            current.left = current.right;
            current.right = temp;
        }
        return root;
    }
}
