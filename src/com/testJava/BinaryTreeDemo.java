package com.testJava;

import apple.laf.JRSUIUtils;
import com.algorithm.aux.TreeNode;

import java.util.Stack;

/**
 * 二叉树的后序遍历
 */
public class BinaryTreeDemo {

    /**
     * 后序遍历
     * @param root
     */
    public void lasScan(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null;
        while(!stack.isEmpty()){
            TreeNode temp = stack.peek();
            if((temp.left==null&&temp.right==null)||(pre!=null&&(pre==temp.left||pre==temp.right))){
                System.out.println(temp.val);
                pre = stack.pop();
            }else{
                if(temp.right!=null){
                    stack.push(temp.right);
                }
                if (temp.left!=null) {
                    stack.push(temp.left);
                }
            }
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inScan(TreeNode root){
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root!=null||!stack.isEmpty()){
            while(root!=null){
                stack.push(root);
                root = root.left;
            }
            TreeNode temp = stack.pop();
            System.out.println(temp.val);
            if (temp.right!=null) {
                root = temp.right;
            }
        }
    }

    public void befScan(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            System.out.println(temp.val);
            if(temp.right!=null){
                stack.push(temp.right);
            }
            if(temp.left!=null){
                stack.push(temp.left);
            }
        }
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t2.right = t5;
        t3.left = t6;
        t3.right = t7;
        BinaryTreeDemo bt  = new BinaryTreeDemo();
//        bt.lasScan(t1);
//        bt.inScan(t1);
        bt.befScan(t1);
    }
}
