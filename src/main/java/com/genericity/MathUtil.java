package com.genericity;

/**
 * @author: yinchao
 * @ClassName: MathUtil
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/2/14 14:44
 */
public class MathUtil {

    // 受限泛型方法：T必须是Number的子类
    public static <T extends Number> double sum(T num1, T num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    public static void main(String[] args) {
        System.out.println(sum(10, 20));           // 输出: 30.0
        System.out.println(sum(3.14, 2.71));       // 输出: 5.85
    }
}
