package com.excel.work;

import com.alibaba.excel.EasyExcel;

public class ClueDemo {

    public static void main(String[] args) {
        // 修改对应的场景id，线索id和任务包id
        String fileName = "/Users/bjhl/prod-tmk-crm.xlsx";
        EasyExcel.read(fileName, WdClueData.class, new ClueDataListener("f445", 140L, 227L)).sheet("小学").doRead();
    }
}
