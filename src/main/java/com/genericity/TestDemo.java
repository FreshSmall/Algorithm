package com.genericity;

import java.util.List;

/**
 * @author: yinchao
 * @ClassName: TestDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/14 14:36
 */
public class TestDemo {

    /**
     * 自定义泛型容器测试
     */
    private void testBox() {
        Box<String> box = new Box<>();
        box.setContent("hello world");
        System.out.println(box.getContent());

        Box<Integer> box1 = new Box<>();
        box1.setContent(123);
        System.out.println(box1.getContent());
    }

    /**
     * 数组工具类测试
     */
    public void testArrayUtils() {
        String[] array = new String[]{"a", "b", "c"};
        List<String> list = ArrayUtils.convertToList(array);
        System.out.println(list);

        Integer[] array1 = new Integer[]{1, 2, 3};
        List<Integer> list1 = ArrayUtils.convertToList(array1);
        System.out.println(list1);
    }


    public static void main(String[] args) {
        TestDemo testDemo = new TestDemo();
        testDemo.testBox();
        testDemo.testArrayUtils();
    }
}
