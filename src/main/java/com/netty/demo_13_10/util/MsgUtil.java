package com.netty.demo_13_10.util;

import com.netty.demo_13_10.dto.MsgDemo1;
import com.netty.demo_13_10.dto.MsgDemo2;
import com.netty.demo_13_10.dto.MsgDemo3;

public class MsgUtil {
    public static MsgDemo1 buildMsgDemo01(String channelId, String msgContent) {
        return new MsgDemo1(channelId, msgContent);
    }

    public static MsgDemo2 buildMsgDemo02(String channelId, String msgContent) {
        return new MsgDemo2(channelId, msgContent);
    }

    public static MsgDemo3 buildMsgDemo03(String channelId, String msgContent) {
        return new MsgDemo3(channelId, msgContent);
    }

}
