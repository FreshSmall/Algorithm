package com.asm.demo1;

public class ApiTest {

    public static void main(String[] args) {
        ApiTest test = new ApiTest();
        String str = test.queryUserInfo(111, 17);
        System.out.println("测试结果：" + str + "\r\n");
    }

    public String queryUserInfo(int uid, int age) {
        return "hello ,young boy!,uid:" + uid + ",age:" + age;
    }
}
