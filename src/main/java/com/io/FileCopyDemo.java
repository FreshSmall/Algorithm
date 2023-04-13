package com.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author: yinchao
 * @ClassName: ZeroCopyFileCopyDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/4/13 14:13
 */
public class FileCopyDemo {

    public static void zeroCopy() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel sourceChannel = FileChannel.open(Paths.get("/Users/bjhl/test-1.hprof"), StandardOpenOption.READ);
        FileChannel destChannel = FileChannel.open(Paths.get("/Users/bjhl/test-3.hprof"), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

        sourceChannel.transferTo(0, sourceChannel.size(), destChannel);

        sourceChannel.close();
        destChannel.close();
        System.out.println("零拷贝花费时间:" + (System.currentTimeMillis() - start));
    }

    public static void nonZeroCopy() throws Exception {
        long start = System.currentTimeMillis();
        FileInputStream inputStream = new FileInputStream("/Users/bjhl/test-2.hprof");
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        FileOutputStream outputStream = new FileOutputStream("/Users/bjhl/test-4.hprof");
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = dataInputStream.read(buffer)) != -1) {
            // 处理数据
            dataOutputStream.write(buffer, 0, bytesRead);
        }
        dataInputStream.close();
        inputStream.close();
        dataOutputStream.close();
        outputStream.close();
        System.out.println("非零拷贝花费时间:" + (System.currentTimeMillis() - start));
    }

    public static void main(String[] args) throws Exception {
        zeroCopy();
        nonZeroCopy();
    }
}
