package com.example.boot.member.authcode;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;


public class AuthCodeCheckTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthCodeCheckTest.class);
    HashMap<String, Object> vars = new HashMap<>();

    @BeforeEach
    public void generate() {
        LOGGER.info("生成验证码");
        String telephone = RandomStringUtils.randomNumeric(11);
        vars.put("telephone", telephone);
        String url = String.format("http://localhost:8070/api/sso/getAuthCode?telephone=%s", telephone);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
        JSONObject bodyJson = JSONObject.parseObject(responseEntity.getBody());
        vars.put("authCode", bodyJson.getString("data"));
    }

    @Test
    public void check() {
        LOGGER.info("校验验证码");
        String url = String.format("http://localhost:8070/api/sso/verifyAuthCode?telephone=%s&authCode=%s",
                vars.get("telephone"), vars.get("authCode"));
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
    }
}
