package com;

/**
 * @author: yinchao
 * @ClassName: ShareValueTest
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/3/6 15:47
 */
public class ShareValueTest {

    private long getShareValue(int index, int total, int unit) {
        double doubleValue = (double) 1 / total;
        if (total - 1 == index) {
            long sum = (long) (unit * doubleValue);
            return unit - sum * (total - 1);
        } else {
            return (long) (unit * doubleValue);
        }
    }

    public static void main(String[] args) {
        Long clazzNumber = 466548162740385792L;
        System.out.println(clazzNumber.hashCode() %128);
    }

}
