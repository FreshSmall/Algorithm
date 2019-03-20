package com.algorithm;

import com.algorithm.auxiliary.TreeNode;

/**
 * 实现两个函数序列化和反序列二叉树
 * 序列化:将二叉树前序遍历成字符串，反序列化；将前序遍历的字符串转换为二叉树
 */
public class Demo61 {


    public String Seriaize(TreeNode root) {

        StringBuffer sb = new StringBuffer();
        if(root == null){
            return null;
        }
        Seriaize(root, sb);
        return sb.toString();
    }

    public void Seriaize(TreeNode root, StringBuffer sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val);
        sb.append(",");
        Seriaize(root.left, sb);
        Seriaize(root.right, sb);
    }


    int index = -1;

    public TreeNode Deserialize(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        String[] strs = str.split(",");
        return Deserialize(strs);
    }

    private TreeNode Deserialize(String[] strs) {
        index++;
        if (!"#".equals(strs[index])) {
            TreeNode treeNode = new TreeNode(Integer.valueOf(strs[index]));
            treeNode.left = Deserialize(strs);
            treeNode.right = Deserialize(strs);
            return treeNode;
        }
        return null;
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
        Demo61 d = new Demo61();
        String str = d.Seriaize(t1);
        System.out.println(str);
        TreeNode root = d.Deserialize(str);
        System.out.println(root.val);
    }
}
