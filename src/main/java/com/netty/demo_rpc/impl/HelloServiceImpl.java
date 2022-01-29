package com.netty.demo_rpc.impl;

import com.netty.demo_rpc.api.HelloService;

public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String param) {
        return "服务端返回信息:" + param;
    }
}
