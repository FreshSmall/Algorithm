package com.threadlocal;

/**
 * @author: yinchao
 * @ClassName: ThreadLocalDemo
 * @Description: ThreadLocal Demo
 * @team wuhan operational dev.
 * @date: 2022/10/24 15:20
 */
public class ThreadLocalDemo {


    public static void main(String[] args) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                ThreadLocal<String> threadLocal = new ThreadLocal<>();
                threadLocal.set("java");
                threadLocal.set("c++");
                System.out.println(threadLocal.get());
            }
        }).start();

        while (true) {

        }
    }

}
