package com.algorithm;

import com.algorithm.auxiliary.TreeNode;

import java.util.Stack;

/**
 * 输入二叉搜索树，将树排列成双端搜索链表
 */
public class Demo26 {
    public TreeNode Convert(TreeNode pRootOfTree) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(pRootOfTree==null){
            return null;
        }
        TreeNode pre = null;
        TreeNode root = null;
        boolean isFirst = true;
        while(!stack.isEmpty()||pRootOfTree!=null){
            while(pRootOfTree!=null){
                stack.push(pRootOfTree);
                pRootOfTree = pRootOfTree.left;
            }
            TreeNode temp = stack.pop();
            if(isFirst){
                root = temp;
                temp.left = root;
                pre = temp;
                isFirst = false;
            }else{
                pre.right = temp;
                temp.left = pre;
                pre =temp;
            }
            if(temp.right!=null){
                pRootOfTree = temp.right;
            }
        }
        return root;
    }

    public void Convert1(TreeNode pRootOfTree,TreeNode pre,TreeNode root) {
        if(pRootOfTree!=null){
            Convert1(pRootOfTree.left,pre,root);
            if(pre==null){
                pre = pRootOfTree;
                root = pRootOfTree;
            }else{
                pRootOfTree.left = pre;
                pre.right = pRootOfTree;
                pre = pRootOfTree;
            }
            Convert1(pRootOfTree.right,pre,root);
        }
    }

    public TreeNode Convert2(TreeNode pRootOfTree){
        TreeNode pre = null;
        TreeNode root = null;
        Convert1(pRootOfTree,pre,root);
        return root;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(2);
        TreeNode t2 = new TreeNode(1);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        Demo26 demo = new Demo26();
    }
}
