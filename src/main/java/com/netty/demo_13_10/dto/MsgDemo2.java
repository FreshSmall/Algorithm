package com.netty.demo_13_10.dto;

import com.netty.demo_13_10.dto.protocol.Command;
import com.netty.demo_13_10.dto.protocol.Packet;

public class MsgDemo2 extends Packet {

    private String channelId;
    private String demo01;

    public MsgDemo2(String channelId, String demo01) {
        this.channelId = channelId;
        this.demo01 = demo01;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getDemo01() {
        return demo01;
    }

    public void setDemo01(String demo01) {
        this.demo01 = demo01;
    }

    @Override
    public Byte getCommand() {
        return Command.Demo02;
    }
}
