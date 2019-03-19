package com.algorithm;

import apple.laf.JRSUIUtils;
import com.algorithm.aux.TreeLinkNode;

/**
 * 二叉树的下一个节点
 * 分三种情况：1、该节点有右子树，2、该节点没有右子树，但是节点是它父节点的左子节点，3、该节点既没有右子树，并且它还是它父节点的右子节点
 */
public class Demo57 {

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if(pNode == null){
            return pNode;
        }
        if(pNode.right!=null){//该节点有右子树
            pNode = pNode.right;
            while(pNode.left!=null){
                pNode = pNode.left;
            }
            return pNode;
        }else{//该节点没有右子树
            TreeLinkNode parent = pNode.next;
            while(parent!=null&&pNode!=parent.left){
                pNode = parent;
                parent = parent.next;
            }
            return parent;
        }
    }

    public static void main(String[] args) {

    }
}
