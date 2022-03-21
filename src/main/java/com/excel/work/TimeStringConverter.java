package com.excel.work;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStringConverter implements Converter<String> {

    private static final String ORIGINAL_FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

    private static final String TARGET_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Override
    public Class supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration){
        SimpleDateFormat originFormat = new SimpleDateFormat(ORIGINAL_FORMAT);
        SimpleDateFormat targetFormat = new SimpleDateFormat(TARGET_FORMAT);

        Date date = null;
        try {
            date = originFormat.parse(cellData.getStringValue());
            return targetFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return cellData.getStringValue();
        }
    }

    public static void main(String[] args) throws ParseException {
        String time = "2022-02-15 18:17:03:247";
        SimpleDateFormat originFormat = new SimpleDateFormat(ORIGINAL_FORMAT);
        SimpleDateFormat targetFormat = new SimpleDateFormat(TARGET_FORMAT);
        Date date = originFormat.parse(time);
        System.out.println(targetFormat.format(date));
    }

}
