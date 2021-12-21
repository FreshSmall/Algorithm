/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_4.dto;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/21 19:17
 **/
public class Constants {

    //Constants.FileStatus ｛0开始、1中间、2结尾、3完成｝
    public static class FileStatus {

        // 开始
        public static int BEGIN = 0;
        // 中间
        public static int CENTER = 1;
        // 结尾
        public static int END = 2;
        // 完成
        public static int COMPLETED = 3;
    }

    // 0:传输文件请求，1:文件传输指令，2：文件传输数据
    public static class TransferType {

        // 0 请求传输文件
        public static int REQUEST = 0;
        // 1 文件传输指令
        public static int INSTRUCT = 1;
        // 文件传输数据
        public static int DATA = 2;
    }


}
