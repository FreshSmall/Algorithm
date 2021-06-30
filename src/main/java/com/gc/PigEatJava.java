/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author bjhl
 * @description
 * @team wuhan operational dev.
 * @date 2021/6/23 16:36
 **/
public class PigEatJava {

    private static volatile List<Byte[]> eat = new ArrayList<>();
    private static volatile int count = 100;

    class PigProducer implements Runnable{

        @Override
        public void run() {

        }
    }

    class PigConsumer implements Runnable{

        @Override
        public void run() {

        }
    }
}
