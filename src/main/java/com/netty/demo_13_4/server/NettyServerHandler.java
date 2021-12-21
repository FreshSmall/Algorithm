/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_4.server;

import com.alibaba.fastjson.JSON;
import com.netty.demo_13_4.dto.Constants;
import com.netty.demo_13_4.dto.FileBurstData;
import com.netty.demo_13_4.dto.FileBurstInstruct;
import com.netty.demo_13_4.dto.FileDespInfo;
import com.netty.demo_13_4.dto.FileTransferProtocol;
import com.netty.demo_13_4.dto.MessageInfo;
import com.netty.demo_13_4.util.CacheUtil;
import com.netty.demo_13_4.util.MsgUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/21 13:56
 **/
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("连接开始");
        String str =
            "通知服务端链接建立成功" + ",时间:" + new Date() + ",地址:" + channel.localAddress().getHostString();
        ctx.writeAndFlush(new MessageInfo(channel.id().toString(), str));
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 数据格式验证
        if (!(msg instanceof FileTransferProtocol)) {
            return;
        }
        FileTransferProtocol fileTransferProtocol = (FileTransferProtocol) msg;
        //0传输文件'请求'、1文件传输'指令'、2文件传输'数据'
        switch (fileTransferProtocol.getTransferType()) {
            case 0:
                FileDespInfo fileDespInfo = (FileDespInfo) fileTransferProtocol.getTransferObj();
                // 断点续传信息，实际应用中需要将断点续传信息保存到数据库中
                FileBurstInstruct fileBurstInstructOld = CacheUtil.burstDataMap
                    .get(fileDespInfo.getFileName());
                // 还有断点的文件没有传完
                if (null != fileBurstInstructOld) {
                    if (fileBurstInstructOld.getStatus() == Constants.FileStatus.COMPLETED) {
                        CacheUtil.burstDataMap.remove(fileDespInfo.getFileName());
                    }
                    // 传输完成删除断点信息
                    System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())
                        + " bugstack虫洞栈服务端，接收客户端传输文件请求[断点续传]。" + JSON
                        .toJSONString(fileBurstInstructOld));
                    ctx.writeAndFlush(MsgUtil.buildTransferInstruct(fileBurstInstructOld));
                    return;
                }
                // 发送信息
                FileTransferProtocol sendFileTransferProtocol = MsgUtil.buildTransferInstruct(Constants.FileStatus.BEGIN, fileDespInfo.getFileUrl(), 0);
                ctx.writeAndFlush(sendFileTransferProtocol);
                System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + " bugstack虫洞栈服务端，接收客户端传输文件请求。" + JSON.toJSONString(fileDespInfo));
                break;
            case 2:
                FileBurstData fileBurstData = (FileBurstData) fileTransferProtocol.getTransferObj();
                FileBurstInstruct fileBurstInstruct = FileU


        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("断开链接，" + ctx.channel().localAddress().toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        System.out.println("异常信息!\r\n" + cause.getMessage());
    }

}
