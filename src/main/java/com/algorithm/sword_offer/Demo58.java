package com.algorithm.sword_offer;

import com.algorithm.auxiliary.TreeNode;

/**
 * 用来判断二叉树是不是对称
 */
public class Demo58 {

	boolean isSymmetrical(TreeNode pRoot) {
		if (pRoot == null) {
			return true;
		}
		return comRoot(pRoot.left, pRoot.right);
	}

	public boolean comRoot(TreeNode left, TreeNode right) {
		if (left == null) {
			return right == null;
		}
		if (right == null) {
			return left == null;
		}
		if (left.val != right.val) {
			return false;
		}
		return comRoot(left.left, right.right) && comRoot(left.right, right.left);
	}

	public static void main(String[] args) {

	}
}
