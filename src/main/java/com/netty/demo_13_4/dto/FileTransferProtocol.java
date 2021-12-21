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
 * @date 2021/12/21 17:24
 **/
public class FileTransferProtocol {

    // 0：请求传输数据，1：文件传输指令，2：文件传输数据
    private Integer transferType;
    // 数据对象；(0)FileDescInfo、(1)FileBurstInstruct、(2)FileBurstData
    private Object transferObj;

    public Integer getTransferType() {
        return transferType;
    }

    public void setTransferType(Integer transferType) {
        this.transferType = transferType;
    }

    public Object getTransferObj() {
        return transferObj;
    }

    public void setTransferObj(Object transferObj) {
        this.transferObj = transferObj;
    }
}
