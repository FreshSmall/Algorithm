package com.io.io_operation;


import java.io.*;

public class IoDemo {
    //    private static String sourcepath = "E:/binlog.000018";
    private String sourcepath = this.getClass().getResource("").getPath();
    private static String targetpath = "E:/copybinlog.000018";


    /**
     * 测试Java文件类的相关问题
     */
    public void testFile() {
        File file = new File(sourcepath);
        File[] strings = file.listFiles();
        file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return false;
            }
        });
        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
    }

    /**
     * 测试输入流，读取文本的内容输出
     * 字节流
     *
     * @throws Exception
     */
    public void testInputStream() throws Exception {
        InputStream inputStream = new FileInputStream(sourcepath);
        File file = new File(targetpath);
        if (!file.exists()) {
            file.createNewFile();
        }
        OutputStream outputStream = new FileOutputStream(file);
        int data = 0;
        byte[] bytes = new byte[1024];
        while ((data = inputStream.read(bytes)) != -1) {
//            outputStream.write(bytes,0,data);
        }
        inputStream.close();
    }

    public void testBufferedInputStream() throws Exception {
        InputStream inputStream = new FileInputStream(sourcepath);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        int data = 0;
        while ((data = bufferedInputStream.read()) != -1) {

        }
        bufferedInputStream.close();
        inputStream.close();
    }

    /**
     * 测试输入，字符，读取文本内容输出
     *
     * @throws Exception
     */
    public void testRead() throws Exception {
        char[] chars = new char[10];
        FileReader reader = new FileReader(sourcepath);
        int data = 0;
        StringBuffer sb = new StringBuffer();
		/*while((data = reader.read(chars))!=-1){
			sb.append(chars,0,data);
		}*/
        while ((data = reader.read()) != -1) {
            System.out.print((char) data);
        }
        reader.close();
    }


    /**
     * 字符缓冲输入流
     *
     * @throws Exception
     */
    public void testBufferReader() throws Exception {
        FileReader reader = new FileReader(sourcepath);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String data = null;
        while ((data = bufferedReader.readLine()) != null) {
            System.out.println(data);
        }
        bufferedReader.close();
        reader.close();
    }

    /**
     * 测试输出流，将文字输出到文本中
     *
     * @throws Exception
     */
    public void testOutputStream() throws Exception {
        OutputStream outputStream = new FileOutputStream(sourcepath);
        String text = "测试文本输出";
        outputStream.write(text.getBytes());
        outputStream.close();
    }

    /**
     * 测试输出流（字节），将文字输出到文本中
     *
     * @throws IOException
     */
    public void testWriter() throws IOException {
        FileWriter writer = new FileWriter(sourcepath);
        String text = "测试文本writer输出";
        writer.write(text);
        writer.write("完毕");
        writer.close();
    }

    /**
     * 测试将字节流包装成字符流输出
     *
     * @throws FileNotFoundException
     */
    public void testInputStreamReader() throws Exception {
        Reader reader = new InputStreamReader(new FileInputStream(sourcepath));
        int data = 0;
        while ((data = reader.read()) != -1) {
            System.out.print((char) data);
        }
        reader.close();
    }

    /**
     * 将字符流包装成字节流输入到文本
     *
     * @throws Exception
     */
    public void testOutputStreamWriter() throws Exception {
        Writer writer = new OutputStreamWriter(new FileOutputStream(sourcepath));
        String text = "测试文本流输出";
        writer.write(text);
        writer.write("完毕");
        writer.close();
    }

    /**
     * 允许将内存的缓冲区当做InputStream使用
     */
    public void testByteArrayInputStream() {

    }

    /**
     * 将String转换成InputStream
     */
    public void testStringBufferInputStream() {

    }

    /**
     * 产生用于写入相关PipedOutputStream的数据。实现"管道化"概念
     */
    public void testPipedInputStream() {

    }

    /**
     * 将两个或多个InputStream对象转换成单一InputStream
     */
    public void testSequenceInputStream() {

    }

    /**
     * 抽象类，作为"装饰器"的接口。其中"装饰器"为其他的
     * InputStream类提供有用功能
     */
    public void testFilterInputStream() {

    }


    /**
     * 在内存中创建缓冲区，所有送往"流"的数据都要放置在次缓冲区
     */
    public void testByteArrayOutputStream() {

    }

    /**
     * 任何写入其中的信息都会自动作为相关PipedInputStream的输出，实现"管道化"概念
     */
    public void testPipedOutputStream() {

    }

    /**
     * 抽象类，作为"装饰器"的接口，其中，"装饰器"为其他OutputStream提供有用功能
     */
    public void testFilterOutputStream() {

    }


    public static void main(String[] args) throws Exception {
        IoDemo ioDemo = new IoDemo();
        /*long start = System.currentTimeMillis();
        ioDemo.testInputStream();
        long end = System.currentTimeMillis();
        System.out.println("花费时间:"+(end-start));*/
        ioDemo.testFile();


        /*long start1 = System.currentTimeMillis();
        ioDemo.testBufferedInputStream();
        long end1 = System.currentTimeMillis();
        System.out.println("Buffered花费时间:"+(end1-start1));*/
    }

}
