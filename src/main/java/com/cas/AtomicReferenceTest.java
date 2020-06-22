package com.cas;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by IntelliJ IDEA.
 *
 * @Author : yinchao
 * @create 2020/6/18 10:50 上午
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {
        AtomicReference atomicReference = new AtomicReference();
        atomicReference.set(1);
        System.out.println(atomicReference.get());

        System.out.println(UUID.randomUUID().toString());
    }
}
