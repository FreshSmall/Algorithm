/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.serialize;

import java.io.Serializable;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/22 15:48
 **/
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String address;
    private String email;

    public Person() {
    }

    public Person(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }


    @Override
    public String toString() {
        return "Person{" +
            "name='" + name + '\'' +
            ", age=" + age +
            ", address='" + address + '\'' +
            ", email='" + email + '\'' +
            '}';
    }
}
