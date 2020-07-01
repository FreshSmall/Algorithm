/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author yinchao
 * @description 测试流数据
 * @team wuhan operational dev.
 * @date 2020/7/1 10:37 上午
 **/
public class StreamDemo {

    private static List<Apple> appleList;

    public static void init(){
        System.out.println("测试地址");
        appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 =  new Apple(1,"苹果1",new BigDecimal("3.25"),10);
        Apple apple12 = new Apple(1,"苹果2",new BigDecimal("1.35"),20);
        Apple apple2 =  new Apple(2,"香蕉",new BigDecimal("2.89"),30);
        Apple apple3 =  new Apple(3,"荔枝",new BigDecimal("9.99"),40);

//        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
    }

    /**
     * 分组
     */
    public void testGroup(){
        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
        System.out.println(groupBy);
    }

    /**
     * list转map
     */
    public void listToMap(){
        Map<Object, Object> appleMap = appleList.stream().collect(Collectors.toMap(p -> p.getId(),p -> p.getName()));
        System.out.println(appleMap);
    }

    /**
     * 过滤filter
     */
    public void testFilter(){
        List<Apple> filterList = appleList.stream().filter(a -> a.getName().equals("香蕉")).collect(Collectors.toList());
        System.out.println(filterList);
    }



    public static void main(String[] args) {
        init();
        StreamDemo demo = new StreamDemo();
        demo.testGroup();
        demo.listToMap();
        demo.testFilter();
    }
}
