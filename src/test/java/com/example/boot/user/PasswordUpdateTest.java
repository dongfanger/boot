package com.example.boot.user;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class PasswordUpdateTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordUpdateTest.class);
    Map<String, Object> vars = new HashMap<>();

    @BeforeEach
    void login() {
        LOGGER.info("登录");
        String url = "http://localhost:8070/api/users/login";
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> body = new HashMap<>();
        body.put("username", "iJWYBI");
        body.put("password", "qa123456");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
        JSONObject bodyJson = JSONObject.parseObject(responseEntity.getBody());
        String token = bodyJson.getString("token");
        assert token != null;
        vars.put("token", token);
    }

    @Test
    void updatePassword() {
        LOGGER.info("修改密码");
        String token = String.valueOf(vars.get("token"));
        token = "Bearer " + token;
        String url = "http://localhost:8070/api/users/passwords/set";
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> body = new HashMap<>();
        body.put("oldPassword", "qa123456");
        body.put("newPassword", "qa123456");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);
        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
    }
}
