package com.io;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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

    public void testReadTextStream(String path) throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        BufferedInputStream inputStream = new BufferedInputStream(fileInputStream);
        try {
            while (inputStream.available() > 0) {
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testReadTextReader(String path) throws IOException {

        FileWriter fileWriter = new FileWriter(new File("/Users/bjhl/IdeaProjects/my-local-project/Algorithm/src/main/resources/MicroSIP1.template"));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        FileReader fileReader = new FileReader(new File(path));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str;
        while (true) {
            try {
                if ((str = bufferedReader.readLine()) == null) break;
                if (str.contains("${sip}")) {
                    str = str.replace("${sip}","cpaas.itelecloud.com:9680");
                }
                bufferedWriter.write(str+"\n");
                System.out.println(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    public void testWriteTextWriter(String path) throws IOException {
        FileWriter fileWriter = new FileWriter(new File(path));
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("测试地址1\n");
        bufferedWriter.write("测试地址2\n");
        bufferedWriter.flush();
        bufferedWriter.close();
        fileWriter.close();

    }

    public static void main(String[] args) {
        IOFileDemo demo = new IOFileDemo();
        try {
            demo.testReadTextReader("/Users/bjhl/IdeaProjects/my-local-project/Algorithm/src/main/resources/MicroSIP.template");
//            demo.testWriteTextWriter("/Users/bjhl/IdeaProjects/my-local-project/Algorithm/src/main/resources/MicroSIP1.template");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
