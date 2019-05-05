package com.algorithm;

/**
 * 二叉搜索树后序遍历序列
 */
public class Demo23 {

    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length==0){
            return true;
        }
        return isSequenceOfBST(sequence,0,sequence.length-1);
    }

    public boolean isSequenceOfBST(int [] sequence,int start,int end){
        if(start==end){
            return true;
        }
        int i = start;
        for(;i<end;i++){
            if(sequence[i]>sequence[end]){
                break;
            }
        }
        for(int j=i;j<end;j++){
            if(sequence[j]<sequence[end]){
                return false;
            }
        }
        return isSequenceOfBST(sequence,start,i-1)&&isSequenceOfBST(sequence,i,end-1);
    }

    public static void main(String[] args) {
        int[] sequence = {1,3,2,5,7,6,4};
        Demo23 d = new Demo23();
        System.out.println(d.VerifySquenceOfBST(sequence));
    }
}
