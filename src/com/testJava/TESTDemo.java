package com.testJava;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class TESTDemo {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = DemoClass.class;
        DemoClass demoClass = (DemoClass) clazz.newInstance();
        Field[]  fields = clazz.getFields();
        Method methods = clazz.getMethod("setMess",String.class);
        Method method1 = clazz.getMethod("setStr",String.class);
        method1.invoke(demoClass,"123");
        methods.invoke(demoClass,"123");
        System.out.println(demoClass.getStr());
        System.out.println(DemoClass.mess);

        DemoClass dd = new DemoClass();
        System.out.println(dd.getMess());
    }
}
