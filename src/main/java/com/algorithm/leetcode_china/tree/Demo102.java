package com.algorithm.leetcode_china.tree;

import com.algorithm.auxiliary.TreeNode;

import java.util.*;

/**
 * 二叉树的层序遍历
 */
public class Demo102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> nodes = new LinkedList<TreeNode>();
        List<List<Integer>> paths = new ArrayList<>();
        if(root==null){
            return paths;
        }
        nodes.add(root);
        int level = 0;
        while (!nodes.isEmpty()) {
            paths.add(new ArrayList<>());
            int level_size = nodes.size();
            for(int i=0;i<level_size;i++){
                TreeNode temp  = nodes.pollFirst();
                paths.get(level).add(temp.val);

                if(temp.left!=null){
                    nodes.add(temp.left);
                }
                if(temp.right!=null){
                    nodes.add(temp.right);
                }

            }

            level++;
        }

        return paths;

    }
}
