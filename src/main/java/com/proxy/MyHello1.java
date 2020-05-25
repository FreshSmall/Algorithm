package com.proxy;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/5/18 4:38 下午
 */
public class MyHello1 implements Hello1 {

    @Override
    public void sayHello() {
        System.out.println("测试发送hello1");
    }
}
