package com.algorithm;

/**
 * 正则表达式匹配
 */
public class Demo52 {


    public boolean match(char[] str, char[] pattern){
        if(str==null||pattern==null){
            return false;
        }
        return matchCore(str,pattern);
    }

    private boolean matchCore(char[] str, char[] pattern) {
        if(str.length==0&&pattern.length==0){
            return true;
        }
        if(str.length!=0&&pattern.length==0){
            return false;
        }

        int i=0;
        int j=0;
        if(pattern[i+1]=='*'){
            if(pattern[i]==str[i]||(pattern[i]=='.'&&str[i]!='\0')){
                return matchCore(null,null);
            }else{
                return false;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
