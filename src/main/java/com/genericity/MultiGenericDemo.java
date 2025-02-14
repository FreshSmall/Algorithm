package com.genericity;

/**
 * @author: yinchao
 * @ClassName: MultiGenericDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/14 14:46
 */
public class MultiGenericDemo {

    public <A,B,C,D,E,F> void testDemo(int ii,String ss,A a, B b, C c,D d,E e,F f){
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
    }

    public static void main(String[] args) {
        MultiGenericDemo multiGenericDemo = new MultiGenericDemo();
        multiGenericDemo.testDemo(1,"hello",1,2,3,4,5,6);
        multiGenericDemo.testDemo(2,"world","a","b","c","d","e","f");
        multiGenericDemo.testDemo(3,"java",new Box<Integer>(),2.2,3.3,4.4,5.5,6.6);
    }
}
