package com.proxy;

import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/5/18 4:29 下午
 */
public class ProxyTest {


    private static void jdkProxyTest() throws Exception {
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Hello1 hello = (Hello1) Proxy.newProxyInstance(ProxyTest.class.getClassLoader(), new Class[]{Hello1.class}, new JdkProxyHandler(new MyHello1()));
        hello.sayHello();

//        Constructor<MyHello1> constructor = MyHello1.class.getConstructor(null);
//        MyHello1 myHello1 = constructor.newInstance(null);
//        myHello1.sayHello();
    }

    private static void cglibProxyTest() {
        Enhancer en = new Enhancer();
        en.setSuperclass(MyHello.class);
        en.setCallback(new CglibProxyHandler(new MyHello()));
        MyHello proxy = (MyHello) en.create();
        proxy.sayHello();
    }


    public static void main(String[] args) throws Exception {
//        cglibProxyTest();
        jdkProxyTest();
    }
}
