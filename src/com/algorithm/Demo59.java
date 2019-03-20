package com.algorithm;

import com.algorithm.auxiliary.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 按之字形顺序打印二叉树
 */
public class Demo59 {

    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<ArrayList<Integer>>();
        if(pRoot==null){
            return arrayLists;
        }
        Stack<TreeNode> s1 = new Stack<TreeNode>();//存放奇数行树节点
        Stack<TreeNode> s2 = new Stack<TreeNode>();//存放偶数行节点
        s1.push(pRoot);
        ArrayList<Integer> arrays = new ArrayList<Integer>();
        arrays.add(pRoot.val);
        while(!s1.isEmpty()||!s2.isEmpty()){
            ArrayList<Integer> array1 = new ArrayList<Integer>();
            while(!s1.isEmpty()){
                TreeNode node = s1.pop();
//                System.out.print(node.val);
                array1.add(node.val);
//                if(s1.isEmpty()){
//                    System.out.println("");
//                }
                if(node.left!=null){
                    s2.push(node.left);
                }
                if(node.right!=null){
                    s2.push(node.right);
                }
            }
            if(array1!=null&&array1.size()>0){
                arrayLists.add(array1);
            }

            ArrayList<Integer> array2= new ArrayList<Integer>();
            while(!s2.isEmpty()){
                TreeNode node = s2.pop();
//                System.out.print(node.val);
                array2.add(node.val);
                /*if(s2.isEmpty()){
                    System.out.println("");
                }*/
                if(node.right!=null){
                    s1.push(node.right);
                }
                if(node.left!=null){
                    s1.push(node.left);
                }
            }
            if(array2!=null&&array2.size()>0){
                arrayLists.add(array2);
            }
        }


        return arrayLists;

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
        Demo59 d = new Demo59();
        d.Print(t1);
    }

}
