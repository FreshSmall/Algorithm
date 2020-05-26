package com.io.netty.serializable;


import org.msgpack.MessagePack;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by IntelliJ IDEA.
 * Java序列化代码 编码测试类
 *
 * @Author : yinchao
 * @create 2020/5/25 9:29 下午
 */
public class TestUserInfo {

    public void testMem() throws IOException {
        UserInfo info = new UserInfo();
        info.setUserID(100);
        info.setUserName("Welcome to Netty");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println("The jdk serializable length is:" + b.length);
        bos.close();
        System.out.println("The byte array serializable length is:" + info.codeC().length);

        MessagePack msgpack = new MessagePack();
        byte[] raw = msgpack.write(info);
        System.out.println("The MessagePack serializable length is:" + raw.length);
    }


    public void testSpeed() throws IOException {
        UserInfo info = new UserInfo();
        info.setUserID(100);
        info.setUserName("Welcome to Netty");
        int loop = 1000000;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        long start = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(info);
            os.flush();

        }
        os.close();
        System.out.println("The jdk serializable speed time is:" + (System.currentTimeMillis() - start) + "ms");
        bos.close();

        start = System.currentTimeMillis();

        for (int i = 0; i < loop; i++) {
            byte[] b = bos.toByteArray();
        }
        System.out.println("The byte array serializable speed time is:" + (System.currentTimeMillis() - start) + "ms");

        MessagePack msgpack = new MessagePack();
        start = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            byte[] raw = msgpack.write(info);
        }

        System.out.println("The byte MessagePack speed time is:" + (System.currentTimeMillis() - start) + "ms");

    }

    public static void main(String[] args) throws IOException {

        TestUserInfo test = new TestUserInfo();
        test.testMem();
        test.testSpeed();

    }
}
