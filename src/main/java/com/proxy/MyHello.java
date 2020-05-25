package com.proxy;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/5/18 4:24 下午
 */
public class MyHello implements Hello {
    @Override
    public void sayHello() {
        System.out.println("测试发送hello信息！");
    }
}
