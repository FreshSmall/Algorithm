package com.algorithm;

import com.algorithm.aux.TreeNode;

/**
 * 判断二叉树是否是平衡二叉树
 */
public class Demo39 {

    /*public boolean IsBalanced_Solution(TreeNode root) {
        int depth=0;
        return IsBalanced(root)!=-1;
    }*/

    public int IsBalanced(TreeNode root){
        if(root==null){
            return 0;
        }
        int left = IsBalanced(root.left);
        if(left==-1){
            return -1;
        }
        int right = IsBalanced(root.right);
        if(right==-1){
            return -1;
        }
        System.out.println(root.val);
        return Math.abs(left-right)>1?-1:Math.max(left,right)+1;
    }


    public boolean IsBalanced_Solution(TreeNode root) {
        if(root==null){
            return true;
        }
        System.out.println(root.val);
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        int diff = left - right;
        if(diff>1||diff<-1){
            return false;
        }
        return IsBalanced_Solution(root.left)&&IsBalanced_Solution(root.right);
    }

    public int TreeDepth(TreeNode root){
        if(root==null){
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return (left>right)?(left+1):(right+1);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t2.right=t5;
        t3.left=t6;
        t3.right=t7;
        Demo39 d = new Demo39();
        System.out.println(d.IsBalanced_Solution(t1));



    }
}
