package com.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/5/19 10:27 上午
 */
public class CglibProxyHandler implements MethodInterceptor {

    private Object target;

    public CglibProxyHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("目标方法执行前");
        Object res = method.invoke(target, args);
        System.out.println("目标方法执行后");
        return res;
    }
}
