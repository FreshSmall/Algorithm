/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_4.util;

import com.netty.demo_13_4.dto.Constants;
import com.netty.demo_13_4.dto.FileBurstData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author yinchao
 * @description 文件读写工具
 * @team wuhan operational dev.
 * @date 2021/12/21 19:39
 **/
public class FileUtil {

    /**
     * 读文件
     *
     * @return
     */
    public static FileBurstData readFile(String fileUrl, Integer readPosition)
        throws IOException {
        File file = new File(fileUrl);
        // r:只读模式， rw:读写模式
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
        randomAccessFile.seek(readPosition);
        byte[] bytes = new byte[1024 * 100];
        int readSize = randomAccessFile.read(bytes);
        if (readSize <= 0) {
            randomAccessFile.close();
            // Constants.FileStatus
            return new FileBurstData(Constants.FileStatus.COMPLETED);
        }
        FileBurstData fileInfo = new FileBurstData();
        fileInfo.setFileUr(fileUrl);
        fileInfo.setFileName(file.getName());
        fileInfo.setBeginPos(readPosition);
        fileInfo.setEndPos(readPosition + readSize);

        // 不足1024 需要拷贝去掉空字节
        if(readSize < 1024*100){

        }

    }

}
