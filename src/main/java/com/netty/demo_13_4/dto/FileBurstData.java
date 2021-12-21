/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_4.dto;

/**
 * @author yinchao
 * @description 文件分片数据
 * @team wuhan operational dev.
 * @date 2021/12/21 17:04
 **/
public class FileBurstData {

    // 客户端文件地址
    private String fileUr;
    // 文件名称
    private String fileName;
    // 开始位置
    private Integer beginPos;
    // 结束位置
    private Integer endPos;
    // 文件字节;再实际应用中可以使用非对称加密，以保证传输信息安全
    private byte[] data;
    // 传输状态。0:开始，1:中间，2:结尾，3:完成
    private Integer status;

    public FileBurstData(){

    }

    public FileBurstData(Integer status){
        this.status = status;
    }

    public String getFileUr() {
        return fileUr;
    }

    public void setFileUr(String fileUr) {
        this.fileUr = fileUr;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(Integer beginPos) {
        this.beginPos = beginPos;
    }

    public Integer getEndPos() {
        return endPos;
    }

    public void setEndPos(Integer endPos) {
        this.endPos = endPos;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
