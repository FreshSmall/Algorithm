package com.netty.demo_rpc.domain;

public class Response {

    //1:成功|-1：失败|0：没有找到对应的代码逻辑
    private int code;
    private Object result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
