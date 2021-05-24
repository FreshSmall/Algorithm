/*
 * Copyright (C) GSX Techedu Inc. All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.http;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * @author yinchao
 * @description
 * @team wuhan operational dev.
 * @date 2021/1/14 下午5:48
 **/
public class HttpUpload {

    public static void main(String[] args) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(5000);
        RestTemplate restTemplate = new RestTemplate(factory);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(
                popHeaders(), headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity("http://localhost:8082/webupload.php", request, String.class);
        System.out.println(responseEntity.getBody());

    }

    private static MultiValueMap<String, Object> popHeaders() {
        FileSystemResource fileSystemResource = new FileSystemResource("/tmp/temp");
        //组装请求体
        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

        map.add("uid", "abcdefg");
        map.add("file", fileSystemResource);
        return map;
    }
}
