package com.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.List;


public class ReadDemoDataListener extends AnalysisEventListener<DemoData> {

    private int firstNum;
    private String firstCode;
    private String indexStr = "XOUT0";
    private List<DemoData> list;

    public ReadDemoDataListener(int firstNum,String firstCode,List<DemoData> list) {
        this.firstNum = firstNum;
        this.firstCode = firstCode;
        this.list = list;
    }

    @Override
    public void invoke(DemoData demoData, AnalysisContext analysisContext) {
        if (!firstCode.equals(demoData.getCode())) {
            firstCode = demoData.getCode();
            firstNum = firstNum + 1;
            demoData.setNumber(indexStr + firstNum + "");
        } else {
            demoData.setNumber(indexStr + firstNum + "");
        }
        list.add(demoData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("读取完毕");
    }
}
