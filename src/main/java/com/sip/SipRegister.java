package com.sip;

import javax.sip.ClientTransaction;
import javax.sip.ListeningPoint;
import javax.sip.SipFactory;
import javax.sip.SipProvider;
import javax.sip.SipStack;
import javax.sip.TransactionState;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.header.FromHeader;
import javax.sip.header.HeaderFactory;
import javax.sip.header.ToHeader;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import java.util.Properties;

/**
 * @author: yinchao
 * @ClassName: SipRegister
 * @Description:
 * @team wuhan operational dev.
 * @date: 2024/3/26 15:48
 */
public class SipRegister {



    public static void main(String[] args) throws Exception {
        // 创建 SipFactory
        SipFactory sipFactory = SipFactory.getInstance();
        AddressFactory addressFactory = sipFactory.createAddressFactory();
        HeaderFactory headerFactory = sipFactory.createHeaderFactory();
        MessageFactory messageFactory = sipFactory.createMessageFactory();

        Properties properties = new Properties();
        properties.setProperty("javax.sip.STACK_NAME", "stack");
        properties.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "32");
        properties.setProperty("gov.nist.javax.sip.LOG_MESSAGE_CONTENT", "true");
        properties.setProperty("gov.nist.javax.sip.DEBUG_LOG", "mss-jsip-debuglog.txt");
        properties.setProperty("gov.nist.javax.sip.SERVER_LOG", "mss-jsip-messages.xml");


        // 设置 SIP 栈
        SipStack sipStack = sipFactory.createSipStack(properties);
        ListeningPoint listeningPoint = sipStack.createListeningPoint("10.22.78.18", 5060, "udp");
        SipProvider sipProvider = sipStack.createSipProvider(listeningPoint);

        // 创建 SIP 地址
        Address fromAddress = addressFactory.createAddress("sip:ss2003510ss5001@phone1.icsoc.net:5091");
        Address toAddress = addressFactory.createAddress("sip:ss2003510ss5001@phone1.icsoc.net:5091");

        // 创建 REGISTER 请求
        Request registerRequest = messageFactory.createRequest(
                "REGISTER sip:phone1.icsoc.net:5091 SIP/2.0\r\n\r\n");

        // 添加 From 和 To 头
        FromHeader fromHeader = headerFactory.createFromHeader(fromAddress, "123456");
        ToHeader toHeader = headerFactory.createToHeader(toAddress, null);
        registerRequest.addHeader(fromHeader);
        registerRequest.addHeader(toHeader);

        // 发送 REGISTER 请求
        ClientTransaction transaction = sipProvider.getNewClientTransaction(registerRequest);
        transaction.sendRequest();

        TransactionState state = transaction.getState();
        System.out.println(state);
        // 等待响应
//        Response response = transaction.getResponse();
//        if (response.getStatusCode() == 200) {
//            System.out.println("SIP 注册成功！");
//        } else {
//            System.out.println("SIP 注册失败：" + response.getReasonPhrase());
//        }
    }
}
