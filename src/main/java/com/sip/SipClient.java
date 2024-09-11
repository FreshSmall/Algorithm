package com.sip;


import javax.sip.ClientTransaction;
import javax.sip.DialogTerminatedEvent;
import javax.sip.IOExceptionEvent;
import javax.sip.ListeningPoint;
import javax.sip.RequestEvent;
import javax.sip.ResponseEvent;
import javax.sip.SipFactory;
import javax.sip.SipListener;
import javax.sip.SipProvider;
import javax.sip.SipStack;
import javax.sip.TimeoutEvent;
import javax.sip.TransactionTerminatedEvent;
import javax.sip.address.Address;
import javax.sip.address.AddressFactory;
import javax.sip.address.SipURI;
import javax.sip.header.ContactHeader;
import javax.sip.header.FromHeader;
import javax.sip.header.HeaderFactory;
import javax.sip.message.MessageFactory;
import javax.sip.message.Request;
import java.util.Properties;

/**
 * @author: yinchao
 * @ClassName: SipClient
 * @Description:
 * @team wuhan operational dev.
 * @date: 2024/3/25 21:32
 */
public class SipClient implements SipListener {

    private SipFactory sipFactory;
    private SipStack sipStack;
    private SipProvider sipProvider;
    private AddressFactory addressFactory;
    private HeaderFactory headerFactory;
    private MessageFactory messageFactory;
    private ListeningPoint listeningPoint;
    private String username;
    private String domain;
    private String password;

    public void init(String username, String domain, String password) throws Exception {
        this.username = username;
        this.domain = domain;
        this.password = password;

        // Create the SIP factory and set the path name.
        sipFactory = SipFactory.getInstance();
        sipFactory.setPathName("gov.nist");

        // Create and set the SIP stack properties.
        Properties properties = new Properties();
        properties.setProperty("javax.sip.STACK_NAME", "stack");
        properties.setProperty("gov.nist.javax.sip.TRACE_LEVEL", "32");
        properties.setProperty("gov.nist.javax.sip.LOG_MESSAGE_CONTENT", "true");
        properties.setProperty("gov.nist.javax.sip.DEBUG_LOG", "mss-jsip-debuglog.txt");
        properties.setProperty("gov.nist.javax.sip.SERVER_LOG", "mss-jsip-messages.xml");

        // Create the SIP stack.
        sipStack = sipFactory.createSipStack(properties);

        // Create the SIP message factory.
        messageFactory = sipFactory.createMessageFactory();

        // Create the SIP header factory.
        headerFactory = sipFactory.createHeaderFactory();

        // Create the SIP address factory.
        addressFactory = sipFactory.createAddressFactory();

        // Create the listening point.
        listeningPoint = sipStack.createListeningPoint("127.0.0.1", 5060, "udp");

        // Create the SIP provider.
        sipProvider = sipStack.createSipProvider(listeningPoint);
        sipProvider.addSipListener(this);
    }

    public void register() throws Exception {
        SipURI fromUri = addressFactory.createSipURI(username, domain);
        Address fromAddress = addressFactory.createAddress(fromUri);
        FromHeader fromHeader = headerFactory.createFromHeader(fromAddress, null);

        SipURI contactUri = addressFactory.createSipURI(username, "127.0.0.1");
        contactUri.setPort(listeningPoint.getPort());
        Address contactAddress = addressFactory.createAddress(contactUri);
        ContactHeader contactHeader = headerFactory.createContactHeader(contactAddress);

        Request registerRequest = messageFactory.createRequest("REGISTER sip:" + domain + " SIP/2.0");
        registerRequest.addHeader(fromHeader);
        registerRequest.addHeader(contactHeader);

        ClientTransaction transaction = sipProvider.getNewClientTransaction(registerRequest);
        transaction.sendRequest();
    }

    // Implement other SipListener methods as needed.
    // ...

    @Override
    public void processRequest(RequestEvent requestEvent) {
        System.out.println("processRequest: " + requestEvent);
    }

    @Override
    public void processResponse(ResponseEvent responseEvent) {
        System.out.println("processResponse: " + responseEvent);
    }

    @Override
    public void processTimeout(TimeoutEvent timeoutEvent) {
        System.out.println("processTimeout: " + timeoutEvent);
    }

    @Override
    public void processIOException(IOExceptionEvent ioExceptionEvent) {
        System.out.println("processIOException: " + ioExceptionEvent);
    }

    @Override
    public void processTransactionTerminated(TransactionTerminatedEvent transactionTerminatedEvent) {
        System.out.println("processTransactionTerminated: " + transactionTerminatedEvent);
    }

    @Override
    public void processDialogTerminated(DialogTerminatedEvent dialogTerminatedEvent) {
        System.out.println("processDialogTerminated: " + dialogTerminatedEvent);
    }

    public static void main(String[] args) throws Exception {
        SipClient sipClient = new SipClient();
        sipClient.init("ss2003510ss5001", "phone1.icsoc.net", "123456");
        sipClient.register();
    }
}
