package com.wechat;

import com.wechat.com.qq.weixin.mp.aes.WXBizMsgCrypt;

/**
 * @author: yinchao
 * @ClassName: WechatThirdParty
 * @Description:
 * @team wuhan operational dev.
 * @date: 2023/6/2 10:50
 */
public class WechatThirdParty {

    public static void main(String[] args) throws Exception{
        //
        // 第三方回复公众平台
        //

        // 需要加密的明文
        String encodingAesKey = "202cb962ac59075b964b07152d234b701c773f3f8dg";
        String token = "digit-authorization";
        String timestamp = "1685850568";
        String nonce = "1758212433";
        String appId = "wx95bb073f21abe887";
        String replyMsg = "<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";

        WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, appId);
       /* String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);
        System.out.println("加密后: " + mingwen);

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
        dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        dbf.setXIncludeAware(false);
        dbf.setExpandEntityReferences(false);

        DocumentBuilder db = dbf.newDocumentBuilder();
        StringReader sr = new StringReader(mingwen);
        InputSource is = new InputSource(sr);
        Document document = db.parse(is);

        Element root = document.getDocumentElement();
        NodeList nodelist1 = root.getElementsByTagName("Encrypt");
        NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

        String encrypt = nodelist1.item(0).getTextContent();
        String msgSignature = nodelist2.item(0).getTextContent();

        String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
        String fromXML = String.format(format, encrypt);*/
        final String fromXML = "<xml><AppId><![CDATA[wx95bb073f21abe887]]></AppId> <Encrypt><![CDATA[OnQpnpz7kwL8tX4PtBXfEA/WgTWPQ+33hlcTh6sBKoKe4wuofAAnZCcQcCAwqgmSPD1pT22xCPLxk3CirY/9e02XlpZ72+QOEtwXT8tU3tHkssd3+DnarYUoeQG8G1g8EL6exZaByFMZirO6C44NEQveFbx0vOfENxxV5US9VIBBO7iNgSH55aKgg5YPSfKClYj4GI/lPBQRhTzUwRDg7iMozeob6BmDbNpT9oFuU0RynafFF5bASHFH06D0IJIUoubYD9DiZgtn5BcjQ6srR/9KpwY3kz6uHWV1mMocpIzmhMGLTJTd0xCHZWG/45rudyHLSQ4HWhN6BFj2qjltcsbzlHJ2htJn3TZVzavYwO69hx4Ky/alAJgfAUJUMoDqEB3aNQzLLBPXv6Zbeac81ICYz5WoLVy3PXMRot9oKn+cdFYIZYc614FDPLqOCq0O6Mnj9ZhqOMXF4XEeKH8Rn3ZtzbclbKYjReHYetvYrQvP9dPwBMRfE9cK7yWV2rT4eQJ5DB6wOrwD0yWL68xwEOuef8ZYtW6OEWIZcjhvbSWheQ5fviTUZEwuF1m5/+IRs6vshAfsYremG8LugVxasrGg8OfPOOyQN9YNRX8PsiObiZo/RMQt04XTGUp9Cj7+UvcquQzGsLm2YXB/rc2x7Gp+cYH3+I2pOwINXLd/Zib3oT+Ocw5tP9MP7dIXdskFrK9WiqIXb8zNUP23F30gKFHXrGEEBjN4nV0MXN8gIOfsTYMjy+twZo8BLctxQZP2LvE7iZ1N4L/FfrfU51Cr2vtWnNQ01I0ojsuaH9VvabdsS+3ccUb4msiJloI5m3T5op4VPANGQItA5UJKspM48Q==]]></Encrypt></xml>";
        final String msgSignature = "637dda74e13bb7f73065d474aa9e8f7f277ad270";

        //
        // 公众平台发送消息给第三方，第三方处理
        //

        // 第三方收到公众号平台发送的消息
        String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
        System.out.println("解密后明文: " + result2);

        //pc.verifyUrl(null, null, null, null);
    }
}
