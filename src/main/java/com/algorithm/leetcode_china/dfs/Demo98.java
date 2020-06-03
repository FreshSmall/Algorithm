package com.algorithm.leetcode_china.dfs;

import com.algorithm.auxiliary.TreeNode;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * 验证二叉搜索树
 *
 * @Author : yinchao
 * @create 2020/6/2 9:35 上午
 */
public class Demo98 {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        double num = Double.MIN_VALUE;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode temp = stack.pop();
            System.out.println(temp.val);
            if (num >= temp.val) {
                return false;
            }
            num = temp.val;
            if (temp.right != null) {
                root = temp.right;
            }
        }
        return true;
    }

    public boolean isValidBST1(TreeNode root) {
        return help(root, null, null);
    }

    private boolean help(TreeNode root, Integer low, Integer high) {
        if (root == null) {
            return true;
        }

        if (low != null && root.val <= low) {
            return false;
        }

        if (high != null && root.val >= high) {
            return false;
        }

        return help(root.left,low,root.val)&&help(root.right,root.val,high);
    }
}
