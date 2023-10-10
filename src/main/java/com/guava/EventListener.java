package com.guava;

import com.google.common.eventbus.Subscribe;

/**
 * @author: yinchao
 * @ClassName: EventListener
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/7/19 16:25
 */
public class EventListener {

    private static int eventsHandled;

    @Subscribe
    public void stringEvent(String event) {
        eventsHandled++;
        System.out.println("监听到注册时间");
    }

    @Subscribe
    public void stringEvent2(String event){
        System.out.println("事件2监听");
    }
}
