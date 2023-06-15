/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.javaagent;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/9/30 16:22
 **/
public class JVMTIThread {

    /*public static void main(String[] args)
        throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for (VirtualMachineDescriptor vmd : list) {
            if (vmd.displayName().endsWith("AccountMain")) {
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                virtualMachine.loadAgent("/Users/bjhl/IdeaProjects/my-local-project/JavaAgentDemo/out/artifacts/JavaAgentDemo_jar/JavaAgentDemo.jar", "cxs");
                System.out.println("ok");
                virtualMachine.detach();

            }
        }
    }*/

}
