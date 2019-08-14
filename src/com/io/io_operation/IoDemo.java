package com.io.io_operation;


import java.io.*;

public class IoDemo {

	/**
	 * 测试输入流，读取文本的内容输出
	 * 字节流
	 *
	 * @throws Exception
	 */
	public void testInputStream() throws Exception {
		InputStream inputStream = new FileInputStream("D:/test.txt");
		int data = 0;
		byte[] bytes = new byte[10];
		StringBuffer sb = new StringBuffer();
		while ((data = inputStream.read(bytes)) != -1) {
			sb.append(new String(bytes, "utf-8"));
		}
		System.out.println(sb.toString());
		inputStream.close();
	}

	public void testInputBufferStream() throws Exception {
		InputStream inputStream = new FileInputStream("D:/test.txt");
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

	}

	/**
	 * 测试输入，字符，读取文本内容输出
	 *
	 * @throws Exception
	 */
	public void testRead() throws Exception {
		char[] chars = new char[10];
		FileReader reader = new FileReader("D:/test.txt");
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
	 * @throws Exception
	 */
	public void testBufferReader() throws Exception {
		FileReader reader = new FileReader("D:/test.txt");
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
		OutputStream outputStream = new FileOutputStream("D:/test.txt");
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
		FileWriter writer = new FileWriter("D:/test.txt");
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
		Reader reader = new InputStreamReader(new FileInputStream("D:/test.txt"));
		int data = 0;
		while ((data = reader.read()) != -1) {
			System.out.print((char) data);
		}
		reader.close();
	}

	/**
	 * 将字符流包装成字节流输入到文本
	 * @throws Exception
	 */
	public void testOutputStreamWriter() throws Exception {
		Writer writer = new OutputStreamWriter(new FileOutputStream("D:/test.txt"));
		String text = "测试文本流输出";
		writer.write(text);
		writer.write("完毕");
		writer.close();
	}


	public static void main(String[] args) throws Exception {
		IoDemo ioDemo = new IoDemo();
		ioDemo.testRead();
	}

}
