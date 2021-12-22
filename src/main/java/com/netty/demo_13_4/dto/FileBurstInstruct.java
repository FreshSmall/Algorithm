/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_4.dto;

/**
 * @author yinchao
 * @description 文件分片指令
 * @team wuhan operational dev.
 * @date 2021/12/21 17:16
 **/
public class FileBurstInstruct {

    // 0开始、1中间、2结尾、3完成
    private Integer status;
    // 客户端文件URL
    private String clientFileUrl;
    // 读取位置
    private Integer readPosition;

    public FileBurstInstruct() {

    }

    public FileBurstInstruct(Integer status) {
        this.status = status;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getClientFileUrl() {
        return clientFileUrl;
    }

    public void setClientFileUrl(String clientFileUrl) {
        this.clientFileUrl = clientFileUrl;
    }

    public Integer getReadPosition() {
        return readPosition;
    }

    public void setReadPosition(Integer readPosition) {
        this.readPosition = readPosition;
    }
}
