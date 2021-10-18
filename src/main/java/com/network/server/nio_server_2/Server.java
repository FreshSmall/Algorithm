/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.network.server.nio_server_2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/10/14 10:44
 **/
public class Server {

    public static void main(String[] args) throws IOException {
        new Acceptor().startUp();
    }

}
