package com.jsonpath;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import java.util.Map;

/**
 * @author: yinchao
 * @ClassName: JsonPathDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/8/16 17:11
 */
public class JsonPathDemo {

    public static void main(String[] args) {

        String json = "onMessage: {\"id_\":{\"value_\":1,\"memoizedIsInitialized\":-1,\"unknownFields\":{\"fields\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"mediaAccountOperator_\":[],\"accountPassword_\":{\"value_\":\"123123\",\"memoizedIsInitialized\":-1,\"unknownFields\":{\"fields\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"updateInfoType_\":{\"value_\":0,\"memoizedIsInitialized\":-1,\"unknownFields\":{\"fields\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0},\"memoizedIsInitialized\":1,\"unknownFields\":{\"fields\":{}},\"memoizedSize\":-1,\"memoizedHashCode\":0}";
        DocumentContext context = JsonPath.parse(json);
        JsonPath compile = JsonPath.compile("$.onMessage");
        context.set(compile, "***");
        System.out.println(context.jsonString());
    }
}
