package com.stream;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yinchao
 * @ClassName: ParallelStreamDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2024/9/10 18:31
 */
public class ParallelStreamDemo {

    public static void main(String[] args) {

        final List<String> strList = Lists.newArrayList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j","k","l","m","n","o","p","q","r","s","t");
        List<List<String>> partition = Lists.partition(strList, 4);
        for (int i = 0; i < 10; i++) {
            List<String> resultList = Collections.synchronizedList(new ArrayList<String>());
            partition.parallelStream().forEach(e -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                resultList.addAll(e.stream().map(String::toUpperCase).collect(Collectors.toList()));
            });
            System.out.println(resultList);
        }
    }
}
