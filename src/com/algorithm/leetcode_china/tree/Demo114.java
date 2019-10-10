package com.algorithm.leetcode_china.tree;

import com.algorithm.auxiliary.TreeNode;

/**
 * 二叉树展开为链表
 */
public class Demo114 {



    private TreeNode pre =  null;

    public void flatten(TreeNode root) {

        if (root==null) {
            return;
        }

        flatten(root.right);
        flatten(root.left);

        root.left=null;
        root.right=pre;
        pre = root;
    }


}
