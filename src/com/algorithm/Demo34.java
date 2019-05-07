package com.algorithm;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查找第一个出现一次字符的位置
 */
public class Demo34 {
	public int FirstNotRepeatingChar(String str) {
		char[] strs = str.toCharArray();
		Map<Character,Integer> map = new LinkedHashMap<Character,Integer>();
		for(int i=0;i<strs.length;i++){
			Integer a  = map.get(strs[i]);
			if(a==null||a==0){
				map.put(strs[i],1);
			}else{
				map.put(strs[i],++a);
			}
		}
		for(Map.Entry<Character,Integer> entry : map.entrySet()){
			if(entry.getValue()==1){
				return str.indexOf(entry.getKey());
			}
		}

		return -1;
	}
	public static void main(String[] args) {
		String str = "abaccdeff";
		Demo34 dd = new Demo34();
		int a = dd.FirstNotRepeatingChar(str);
		System.out.println(a);
	}
}
