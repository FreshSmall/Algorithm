package com.excel.work;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class WdClueData {

    @ExcelProperty("user_number")
    private long user_number;
    @ExcelProperty(value = "faculty_name", converter = CustomStringConverter.class)
    private String department;
    @ExcelProperty("subject_name_lvl2")
    private String twoLevelSubject;
    @ExcelProperty("subject_name_lvl1")
    private String oneLevelSubject;
    @ExcelProperty("teacher_realname")
    private String mainTeacher;
    @ExcelProperty(value = "loss_time", converter = TimeStringConverter.class)
    private String lossTime;
    @ExcelProperty("course_type_name")
    private String courseType;
    @ExcelProperty("course_number")
    private String courseNumber;
    @ExcelProperty("course_name")
    private String courseName;
    @ExcelIgnore
    private String type = "W";
}
