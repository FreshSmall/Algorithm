package com.guava;

import com.google.common.eventbus.EventBus;

/**
 * @author: yinchao
 * @ClassName: GuavaDemo
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/7/19 16:16
 */
public class GuavaDemo {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        EventListener listener = new EventListener();
        eventBus.register(listener);
        eventBus.post("");
    }
}
