package com.genericity;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.List;

/**
 * @author: yinchao
 * @ClassName: ArrayUtils
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/14 14:39
 */
public class ArrayUtils {

    public static <T> List<T> convertToList(T[] array) {
        List<T> list = Lists.newArrayList();
        list.addAll(Arrays.asList(array));
        return list;
    }
}
