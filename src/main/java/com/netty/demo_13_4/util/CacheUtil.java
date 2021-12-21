/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.netty.demo_13_4.util;

import com.netty.demo_13_4.dto.FileBurstInstruct;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/12/21 17:41
 **/
public class CacheUtil {

    public static Map<String, FileBurstInstruct> burstDataMap = new ConcurrentHashMap<>();

}
