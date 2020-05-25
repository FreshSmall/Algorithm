package com.algorithm.leetcode_china.tree;


import com.algorithm.auxiliary.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的最近公共祖先
 */
public class Demo236 {

    private static List<TreeNode> path = new ArrayList<>();
    private static List<TreeNode> paths = new ArrayList<>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (hasNode(root.left, p) && hasNode(root.left, q)) {
            return lowestCommonAncestor(root.left, p, q);
        }

        if (hasNode(root.right, p) && hasNode(root.right, q)) {
            return lowestCommonAncestor(root.right, p, q);
        }
        return root;
    }

    private boolean hasNode(TreeNode root, TreeNode q) {
        if (root == null) {
            return false;
        }
        if (root == q) {
            return true;
        }
        return hasNode(root.left, q) || hasNode(root.right, q);
    }

    public void findPath(TreeNode root, TreeNode p) {
        path.add(root);
        if (root == p) {
            paths.addAll(path);
            return;
        }
        if (root == null) {
            return;
        }
        if (root.left == null || root.right == null) {
            path.remove(path.size() - 1);
            return;
        }
        findPath(root.left, p);
        findPath(root.right, p);
        path.remove(path.size() - 1);
        return;

    }

    public static void main(String[] args) {
        Demo236 demo = new Demo236();
        TreeNode r1 = new TreeNode(1);
        TreeNode r2 = new TreeNode(2);
        TreeNode r3 = new TreeNode(3);
        TreeNode r4 = new TreeNode(4);
        TreeNode r5 = new TreeNode(5);
        TreeNode r6 = new TreeNode(6);
        TreeNode r7 = new TreeNode(7);
        r1.left = r2;
        r1.right = r3;
        r2.left = r4;
        r2.right = r5;
        r3.left = r6;
        r3.right = r7;

        demo.findPath(r1, r7);

        for (int i = 0; i < paths.size(); i++) {
            System.out.println(paths.get(i).val);
        }
    }


}
