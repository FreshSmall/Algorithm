package com.algorithm.sword_offer;

/**
 * 表示数值的字符串
 * 使用正则表达式
 */
public class Demo53 {

	public boolean isNumeric(char[] str) {
		String s = new String(str);
		String pattren = "[+-]?[0-9]*(\\.[0-9]*)?([eE][+-]?[0-9]+)?";
		return s.matches(pattren);
	}

	public static void main(String[] args) {
		Demo53 d = new Demo53();
		char[] str = {'1','2','3'};
		System.out.println(d.isNumeric(str));

	}
}
