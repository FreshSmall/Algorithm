package com.excel.home;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class DemoData {

    @ExcelProperty(value = {"单据编号"},index = 0)
    private String number;
    @ExcelProperty(value = {"客户代码"},index = 1)
    private String code;

}
