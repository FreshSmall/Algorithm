package com.excel.home;

import com.alibaba.excel.EasyExcel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelDemo {

    private List<DemoData> read() {
        List<DemoData> list = new ArrayList<>();
        String fileName = "/Users/bjhl/k3-test-1.xls";
        String indexStr = "XOUT0";
        String firstCode = "";
        int firstNumber = 26996;
        EasyExcel.read(fileName, DemoData.class, new ReadDemoDataListener(firstNumber, firstCode, list)).sheet().doRead();
        return list;
    }


    public static void main(String[] args) throws FileNotFoundException {
        ExcelDemo demo = new ExcelDemo();
        List<DemoData> list = demo.read();
        // 写入文件生成新文件
        String newFileName = "/Users/bjhl/k3-test-2.xls";
        EasyExcel.write(newFileName, DemoData.class)
                .sheet("模板")
                .doWrite(list);
        FileOutputStream stream = new FileOutputStream(newFileName);
    }

}
