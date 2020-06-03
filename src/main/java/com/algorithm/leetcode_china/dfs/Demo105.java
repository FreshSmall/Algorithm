package com.algorithm.leetcode_china.dfs;

import com.algorithm.auxiliary.TreeNode;

/**
 * Created by IntelliJ IDEA.
 * 从前序和中序的序列构造二叉树
 *
 * @Author : yinchao
 * @create 2020/6/3 9:53 上午
 */
public class Demo105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return rebuildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    private TreeNode rebuildTree(int[] preorder, int[] inorder, int prelow, int prehigh, int inlow, int inhigh) {
        if (prelow > prehigh) {
            return null;
        }
        int rootVal = preorder[prelow];
        TreeNode root = new TreeNode(rootVal);
        int index = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }
        root.left = rebuildTree(preorder, inorder, prelow + 1, prelow+index-inlow, inlow, index - 1);
        root.right = rebuildTree(preorder, inorder, prelow+index-inlow + 1, prehigh, index + 1, inhigh);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3, 9, 20, 15, 7};
        int[] inorder = new int[]{9, 3, 15, 20, 7};
        Demo105 d = new Demo105();
        TreeNode root = d.buildTree(preorder, inorder);
        System.out.println(root.val);
    }
}
