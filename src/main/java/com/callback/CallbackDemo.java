package com.callback;

public class CallbackDemo {

    public static void main(String[] args) throws InterruptedException{
        Student s = new Student();
        Teacher t = new Teacher(s);
        t.question();
    }
}
