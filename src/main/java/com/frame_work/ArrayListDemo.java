/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.frame_work;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/5/27 2:00 下午
 **/
public class ArrayListDemo {

    public static void main(String[] args) {
        List<String> listcti = new ArrayList<>();
        List<String> listcall = new ArrayList<>();
        listcti.add("1001");
        listcti.add("1002");
        listcti.add("1003");
        listcall.add("1003");
        listcall.add("1002");
        listcall.add("1001");
        listcti.removeAll(listcall);
        System.out.println(listcti.toString());
    }
}
