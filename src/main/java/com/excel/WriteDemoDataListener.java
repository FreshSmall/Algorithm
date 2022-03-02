package com.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;


public class WriteDemoDataListener extends AnalysisEventListener<DemoData> {
    private Map<String, String> map;

    public WriteDemoDataListener(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
