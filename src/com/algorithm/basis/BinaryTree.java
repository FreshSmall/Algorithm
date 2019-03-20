package com.algorithm.basis;

import com.algorithm.auxiliary.TreeNode;

import java.util.LinkedList;
import java.util.Stack;

/**
 * 二叉树遍历前序，中序，后序
 */
public class BinaryTree {

    static LinkedList<Integer> list = new LinkedList<Integer>();
    private Stack<TreeNode> stack = new Stack<TreeNode>();

    /**
     * 二叉树前序遍历递归
     *
     * @param pRoot
     */
    public void befScan(TreeNode pRoot) {
        if (pRoot != null) {
            System.out.println(pRoot.val);
            list.addLast(pRoot.val);
            befScan(pRoot.left);
            befScan(pRoot.right);
        }
    }

    /**
     * 二叉树前序遍历非递归
     */
    public void befScanRec(TreeNode pRoot) {
        if (pRoot != null) {
            stack.add(pRoot);
            while (!stack.isEmpty()) {
                TreeNode temp = stack.pop();
                if (temp != null) {
                    System.out.println(temp.val);
                    stack.add(temp.right);
                    stack.add(temp.left);
                }
            }
        }
    }


    /**
     * 二叉树中序遍历
     *
     * @param pRoot
     */

    public void inScan(TreeNode pRoot) {
        if (pRoot != null) {
            inScan(pRoot.left);
            list.add(pRoot.val);
            System.out.println(pRoot.val);
            inScan(pRoot.right);
        }
    }

    /**
     * 二叉树中序遍历非递归
     */
    public void inScanRec(TreeNode pRoot) {
        while (pRoot != null || !stack.isEmpty()) {
            while (pRoot != null) {
                stack.add(pRoot);
                pRoot = pRoot.left;
            }
            if (!stack.isEmpty()) {
                TreeNode temp = stack.pop();
                System.out.println(temp.val);
                pRoot = temp.right;
            }
        }

    }

    /**
     * 二叉树后序遍历
     *
     * @param pRoot
     */
    public void lasScan(TreeNode pRoot) {
        if (pRoot != null) {
            lasScan(pRoot.left);
            lasScan(pRoot.right);
            list.add(pRoot.val);
            System.out.println(pRoot.val);
        }
    }

    /**
     * 二叉树后续遍历非递归
     */
    public void lasScanRec(TreeNode pRoot) {
        TreeNode cur, pre = null;
        stack.push(pRoot);
        while (!stack.isEmpty()) {
            cur = stack.peek();
            if ((cur.left == null && cur.right == null) || (pre != null && (pre == cur.left || pre == cur.right))) {
                TreeNode temp = stack.pop();
                System.out.println(temp.val);
                pre = temp;
            } else {
                if (cur.right != null) {
                    stack.push(cur.right);
                }
                if (cur.left != null) {
                    stack.push(cur.left);
                }
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
        BinaryTree bt = new BinaryTree();
        /*bt.befScan(t1);
        System.out.println("==========");
        bt.inScan(t1);
        System.out.println("==========");
        bt.lasScan(t1);*/
//        bt.befScanRec(t1);
//        bt.inScanRec(t1);
//        bt.lasScanRec(t1);
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(t1);
        while (!list.isEmpty()) {
            TreeNode temp = list.poll();
            System.out.println(temp.val);
            if (temp.left!=null) {
                list.add(temp.left);
            }
            if (temp.right!=null) {
                list.add(temp.right);
            }
        }
    }
}
