package com.asm.demo1;

import java.lang.instrument.Instrumentation;

public class PreMain {

    // JVM 首先尝试在代理类上调用以下方法
    public static void premain(String agentArgs, Instrumentation list) {
        list.addTransformer(new ProfilingTransformer());
    }
}
