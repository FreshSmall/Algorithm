package com.io.io_operation;


import java.io.File;
import java.io.IOException;

/**
 * File相关的操作集
 */
public class IOFileDemo {

    public void testFileUtils() throws IOException {
        File file = new File("");
        file.createNewFile();
        file.exists();
    }

    public static void main(String[] args) {

    }
}
