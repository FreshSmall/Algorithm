package com.regular;

/**
 * @author: yinchao
 * @ClassName: RegularDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/1/30 09:46
 */
public class RegularDemo {

    public static void main(String[] args) {
        /*Pattern p = Pattern.compile("上课.*书$");
        Matcher m = p.matcher("上课英雄招股书可以使用的case");
        while (m.find()) {
            System.out.println(m.group());
        }*/
        String input = "450,国家.{0,3}领导人推荐,掌门人(幼升小|小升初|初升高|升学),提分";
        //String input = "A,B,C,D,E,F,G,H,I,J";
        String[] parts = input.split(",(?![^{]*\\})");
        //String[] parts = input.split(",(?![^(]*\\\\)\\)");
        //String[] parts = input.split(",(?![^{|(]*\\(}|\\))");

        for (String part : parts) {
            System.out.println(part);
        }
    }
}
