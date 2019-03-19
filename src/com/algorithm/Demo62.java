package com.algorithm;

import com.algorithm.aux.TreeNode;

/**
 * 给定一个二叉搜索树，找出其中最小的节点
 * 中序遍历中第k大的节点
 */
public class Demo62 {


    TreeNode KthNode(TreeNode pRoot, int k){
        if(pRoot ==null||k==0){
            return null;
        }
        return KthnodeCore(pRoot,k);
    }

    private TreeNode KthnodeCore(TreeNode pRoot, int k) {
        TreeNode target = null;
        if(pRoot!=null){
            target = KthnodeCore(pRoot.left,k);
        }

        if(target == null){
            if(k==1){
                target = pRoot;
            }else{
                k--;
            }
        }
        if(target==null&&pRoot.right!=null){
            target = KthnodeCore(pRoot.right,k);
        }
        return target;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(3);
        TreeNode t3 = new TreeNode(7);
        TreeNode t4 = new TreeNode(2);
        TreeNode t5 = new TreeNode(4);
        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(8);
        t1.left=t2;
        t1.right=t3;
        t2.left=t4;
        t2.right=t5;
        t3.left=t6;
        t3.right=t7;
        Demo62 d = new Demo62();
        TreeNode d1 = d.KthNode(t1,3);
        System.out.println(d1.val);
    }
}
