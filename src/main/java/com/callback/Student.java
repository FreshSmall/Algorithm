package com.callback;

public class Student {

    public void calculate(Teacher teacher) throws InterruptedException {
        System.out.println("正在开始计算，思考中....");
        Thread.sleep(1000);
        teacher.answer();
    }

}
