package com.example.boot.member.authcode;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.boot.user.QueryTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class AuthCodeGenerateTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthCodeGenerateTest.class);

    @Test
    public void generate() {
        LOGGER.info("生成验证码");
        String url = "http://localhost:8070/api/sso/getAuthCode?telephone=18600010002";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
    }
}
