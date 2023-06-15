package com.crawler;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author: yinchao
 * @ClassName: FrameDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/5/20 18:54
 */
public class FrameDemo {

    public static void main(String[] args) {
        byte[] binaryData = {72, 101, 108, 108, 111, 44, 32, 87, 111, 114, 108, 100, 33}; // 示例二进制数据

        Charset charset = Charset.forName("UTF-8"); // 指定字符编码方式
        String text = charset.decode(ByteBuffer.wrap(binaryData)).toString();

        System.out.println("转换后的文本：" + text);
    }
}
