package com.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

public class ExcelDemo {

    private List<DemoData> read() {
        List<DemoData> list = new ArrayList<>();
        String fileName = "/Users/bjhl/test1234.xlsx";
        // 读取文件
        EasyExcel.read(fileName, DemoData.class, new PageReadListener<DemoData>(dataList -> {
            int firstNumber = 0;
            String firstCode = "";
            for (DemoData demoData : dataList) {
                System.out.println("读取到一条数据" + JSON.toJSONString(demoData));
                if (!firstCode.equals(demoData.getCode())) {
                    firstCode = demoData.getCode();
                    firstNumber = firstNumber + 1;
                }
                firstCode = demoData.getCode();
                demoData.setNumber(firstNumber+"");
                list.add(demoData);
            }
        })).sheet().doRead();
        return list;
    }


    public static void main(String[] args) {
        ExcelDemo demo = new ExcelDemo();
        List<DemoData> list = demo.read();
        // 写入文件生成新文件
        String newFileName = "/Users/bjhl/test1234_new.xlsx";
        EasyExcel.write(newFileName, DemoData.class)
                .sheet("模板")
                .doWrite(() -> {
                    // 分页查询数据
                    return list;
                });
    }

}
