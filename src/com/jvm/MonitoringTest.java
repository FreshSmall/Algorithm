package com.jvm;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MonitoringTest {

    static class OOMObject{
        public byte[] placeholder = new byte[1024*1024*10];
    }

    static class OOMObject1{
        public byte[] placeholder = new byte[1024*1024*50];
    }

    public static void fillHeap(int num) throws InterruptedException {
//        Thread.sleep(30000);
        OOMObject1 obj = new OOMObject1();
        WeakReference<Object> wf = new WeakReference<Object>(obj);
        obj = null;
        wf.get();//有时候会返回null
        System.out.println("开始创建对象");
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            //稍作延迟，令监视曲线的变化更加明显
            Thread.sleep(50);
            list.add(new OOMObject());
        }


//        System.gc();
//        list.add(new OOMObject());
    }
    public static void main(String[] args) throws InterruptedException {
        fillHeap(5);
        System.out.println("所有对象已经创建完毕");
        while (true) {

        }
    }


}
