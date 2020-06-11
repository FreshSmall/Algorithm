package com.algorithm.leetcode_china.dfs;

import com.algorithm.auxiliary.TreeNode;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * 二叉树展开为链表
 * @Author : yinchao
 * @create 2020/6/10 10:55 上午
 */
public class Demo114 {

    public void flatten(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        if(root==null){
            return;
        }
        TreeNode h1 = null;
        TreeNode h2 = h1;
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode temp = stack.pop();
            if(temp!=null){
                stack.add(temp.right);
                stack.add(temp.left);
                if (h1==null) {
                    h1 = temp;
                }else{
                    h1.right = temp;
                    h1.left = null;
                    h1 = temp;
                }
            }
        }
        root = h2;
    }
}
