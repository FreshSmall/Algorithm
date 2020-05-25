package com.algorithm.leetcode_china.tree;


import com.algorithm.auxiliary.TreeNode;


/**
 * 路径总和
 */
public class Demo437 {


    public int pathSum(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        return findSum(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);

    }

    private int findSum(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (target == root.val) {
            res += 1;
        }
        res += findSum(root.left, target - root.val);
        res += findSum(root.right, target - root.val);
        return res;
    }

    public static void main(String[] args) {
        Demo437 demo = new Demo437();
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

        System.out.println(demo.pathSum(r1, 8));
    }

}
