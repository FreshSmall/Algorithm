package com.algorithm;

import com.algorithm.auxiliary.TreeNode;

/**
 * 求二叉树的深度
 * 递归思路：二叉树子树的高度加上1
 */
public class Demo38 {

    public int Tree_Depth(TreeNode root){
        if(root==null){
            return 0;
        }
        System.out.println(root.val);
        int left = Tree_Depth(root.left);
        int right = Tree_Depth(root.right);
        return Math.max(left,right)+1;
    }

    public static void main(String[] args) {
       /* TreeNode t1 = new TreeNode(1);
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
        t5.left=t6;
//        t3.right=t7;
        Demo38 d = new Demo38();
        System.out.println(d.Tree_Depth(t1));*/
       for(int i=0;i<2;i++){
           String usage = "上报";
           if(i==1){
               usage = "核查";
           }
           System.out.println(usage);
       }
    }
}
