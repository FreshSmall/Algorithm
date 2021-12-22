/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.serialize;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/22 15:49
 **/
public class PersionTest {

    public void serialize() throws Exception {
        File file = new File("/tmp/Person.out");
        // 序列化
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        Person p = new Person("小明", 20, "武汉");
        out.writeObject(p);
    }

    public void deserialize() throws Exception {
        // 反序列化
        File file = new File("/tmp/Person.out");
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
        Person p = (Person) in.readObject();
        in.close();
        System.out.println(p);
    }

    public static void main(String[] args) throws Exception {
        PersionTest test = new PersionTest();
        // test.serialize();
        test.deserialize();
    }

}
