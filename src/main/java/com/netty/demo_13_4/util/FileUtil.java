/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_4.util;

import com.netty.demo_13_4.dto.Constants;
import com.netty.demo_13_4.dto.FileBurstData;
import com.netty.demo_13_4.dto.FileBurstInstruct;

import java.io.File;
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
        if (readSize < 1024 * 100) {
            byte[] copy = new byte[readSize];
            System.arraycopy(bytes, 0, copy, 0, readSize);
            fileInfo.setData(copy);
            fileInfo.setStatus(Constants.FileStatus.END);
        } else {
            fileInfo.setData(bytes);
            fileInfo.setStatus(Constants.FileStatus.CENTER);
        }
        randomAccessFile.close();
        return fileInfo;
    }

    /**
     * 写文件
     *
     * @param baseUrl
     * @param fileBurstData
     * @return
     */
    public static FileBurstInstruct writeFile(String baseUrl, FileBurstData fileBurstData)
        throws IOException {
        if (Constants.FileStatus.COMPLETED == fileBurstData.getStatus()) {
            return new FileBurstInstruct(Constants.FileStatus.COMPLETED);
        }

        File file = new File(baseUrl + "/" + fileBurstData.getFileName());
        // r:只读模式，rw:读写模式
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        // 移动文件记录指针位置
        randomAccessFile.seek(fileBurstData.getBeginPos());
        // 调用了seek（start）方法，从start字节开始写数据
        randomAccessFile.write(fileBurstData.getData());
        randomAccessFile.close();

        if (Constants.FileStatus.END == fileBurstData.getStatus()) {
            return new FileBurstInstruct(Constants.FileStatus.COMPLETED);
        }

        // 文件分片传输指令
        FileBurstInstruct fileBurstInstruct = new FileBurstInstruct();
        // 文件读取状态
        fileBurstInstruct.setStatus(Constants.FileStatus.CENTER);
        // 客户端文件url
        fileBurstInstruct.setClientFileUrl(fileBurstData.getFileUr());
        // 文件读取位置
        fileBurstInstruct.setReadPosition(fileBurstData.getEndPos() + 1);
        return fileBurstInstruct;
    }

}
