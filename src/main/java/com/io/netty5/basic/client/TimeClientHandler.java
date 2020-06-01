package com.io.netty5.basic.client;

import com.io.netty5.serializable.UserInfo;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/5/24 10:07 下午
 */
public class TimeClientHandler extends ChannelHandlerAdapter {


    private ByteBuf firstMessage;

    private byte[] req;

    private int count;

    public TimeClientHandler() {
//        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
        req = ("QUERY TIME ORDER!$_").getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive");

         ByteBuf message = null;
         for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }

        UserInfo[] infos = userInfo();
        for (UserInfo info : infos) {
            ctx.writeAndFlush(info);
        }
        System.out.println("消息发送完毕");
    }


    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        System.out.println(++count + "---" + Thread.currentThread().getName() + ",Server return Message：" + msg);
        /*ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("Now is:" + msg + ";the counter is :" + ++count);*/
    }

    public void exception(ChannelHandlerContext ctx, Throwable cause) {
        //释放资源
        System.out.println("error message:" + cause.getMessage());
        ctx.close();
    }

    private UserInfo[] userInfo() {
        UserInfo[] userInfos = new UserInfo[2];
        UserInfo userInfo = null;
        for (int i = 0; i < 2; i++) {
            userInfo = new UserInfo();
            userInfo.setUserID(i);
            userInfo.setUserName("ABCDEFG--->" + i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }


}
