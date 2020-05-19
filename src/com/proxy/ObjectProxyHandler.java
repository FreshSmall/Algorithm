package com.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/5/18 4:25 下午
 */
public class ObjectProxyHandler implements InvocationHandler {

    private Object target;

    public ObjectProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("目标方法执行前");
        Object res = method.invoke(target, args);
        System.out.println("目标方法执行后");
        return res;
    }
}
