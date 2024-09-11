package com.sip;

import lombok.Data;

import javax.sip.address.URI;


/**
 * @author: yinchao
 * @ClassName: ChallengeResponse
 * @Description:
 * @team wuhan operational dev.
 * @date: 2024/3/25 23:45
 */
@Data
public class ChallengeResponse {

    private String algorithm;
    private String username;
    private String realm;
    private String nonce;
    private URI URI;
    private String password;
    private String method;
}
