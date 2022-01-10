package com.netty.demo_13_10.dto.protocol;

import com.netty.demo_13_10.dto.MsgDemo1;
import com.netty.demo_13_10.dto.MsgDemo2;
import com.netty.demo_13_10.dto.MsgDemo3;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PacketClazzMap {

    public final static Map<Byte, Class<? extends Packet>> packetTypeMap = new ConcurrentHashMap<>();

    static {
        packetTypeMap.put(Command.Demo01, MsgDemo1.class);
        packetTypeMap.put(Command.Demo02, MsgDemo2.class);
        packetTypeMap.put(Command.Demo03, MsgDemo3.class);
    }
}
