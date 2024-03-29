/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.stream;

import com.google.common.collect.Sets;
import org.apache.commons.compress.utils.Lists;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author yinchao
 * @description 测试流数据
 * @team wuhan operational dev.
 * @date 2020/7/1 10:37 上午
 **/
public class StreamDemo {

    private static List<Apple> appleList;
    private static List<String> list = new ArrayList<>();

    public static void init() {
        System.out.println("测试地址");
        appleList = new ArrayList<>();//存放apple对象集合

        Apple apple1 = new Apple(1, "苹果1", new BigDecimal("3.25"), 10);
        Apple apple12 = new Apple(1, "苹果2", new BigDecimal("1.35"), 20);
        Apple apple2 = new Apple(2, "香蕉", new BigDecimal("2.89"), 30);
        Apple apple3 = new Apple(3, "荔枝", new BigDecimal("9.99"), 40);

        list.add("香蕉");
        list.add("荔枝");


        appleList.add(apple1);
        appleList.add(apple12);
        appleList.add(apple2);
        appleList.add(apple3);
    }

    /**
     * 分组
     */
    public void testGroup() {
        Map<Integer, List<Apple>> groupBy = appleList.stream().collect(Collectors.groupingBy(Apple::getId));
        System.out.println(groupBy);
    }

    /**
     * list转map
     */
    public void listToMap() {
        Map<Object, Object> appleMap = appleList.stream().collect(Collectors.toMap(p -> p.getId(), p -> p.getName()));
        System.out.println(appleMap);
    }

    /**
     * 过滤filter
     */
    public void testFilter() {
        List<Apple> filterList = appleList.stream().filter(a -> a.getName().equals("香蕉1")).collect(Collectors.toList());
        System.out.println(filterList);
    }

    public void testListFilter() {
        List<Apple> filterList = appleList.stream().filter(e -> {
            for (String s : list) {
                if (s.equals(e.getName())) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
        System.out.println(filterList);
    }

    public void testMap() {
        Map<String, String> map = new HashMap<>();
        map.put("java", "java1");
        map.put("c#", "c#1");
        map.put("python", "python1");
        map.put("golang", "golang1");

        Apple apple1 = new Apple(1, "java", new BigDecimal("3.25"), 10);
        Apple apple12 = new Apple(1, "苹果2", new BigDecimal("1.35"), 20);
        Apple apple2 = new Apple(2, "香蕉", new BigDecimal("2.89"), 30);
        Apple apple3 = new Apple(3, "荔枝", new BigDecimal("9.99"), 40);

        List<Apple> list = Lists.newArrayList();
        list.add(apple1);
        list.add(apple12);
        list.add(apple2);
        list.add(apple3);

        List<Apple> updateList = list.stream().filter(e -> map.containsKey(e.getName())).peek(e -> e.setNum(100)).collect(Collectors.toList());

        System.out.println(updateList);
    }


    public static void main(String[] args) {
        Set<String> set1 = Sets.newHashSet("1","2","3");
        Set<String> set2 = Sets.newHashSet("4","2","3","5");
        Set<String> set3 = Sets.difference(set1,set2);
        Set<String> set4 = Sets.difference(set2,set1);
        System.out.println(set3);
        System.out.println(set4);
    }
}
