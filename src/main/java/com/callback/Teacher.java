package com.callback;

public class Teacher {

    private Student student;

    public Teacher(Student student) {
        this.student = student;
    }

    public void question() {
        System.out.println("请开始计算");
        try {
            student.calculate(this);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void answer(){
        System.out.println("答案已经计算完成");
    }
}
