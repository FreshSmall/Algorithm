package com.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yinchao
 * @ClassName: G1PressureDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2025/9/13 23:58
 */
public class G1PressureDemo {

    public static void main(String[] args) {
        List<byte[]> holder = new ArrayList<>();
        while (true) {
            // 模拟持续分配中大对象（128KB以上直接进老年代）
            holder.add(new byte[1024 * 256]); // 256KB
            try {
                Thread.sleep(10); // 控制节奏
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
