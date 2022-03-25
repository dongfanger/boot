package com.example.boot.user;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class LoginTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginTest.class);

    @BeforeEach
    public void register() {
        LOGGER.info("先注册");
        String url = "http://localhost:8070/api/users";
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> body = new HashMap<>();
        body.put("username", "admin");
        body.put("password", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
            System.out.println(responseEntity.getBody());
        } catch (HttpServerErrorException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void login_admin() {
        LOGGER.info("登录");
        String url = "http://localhost:8070/api/users/login";
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> body = new HashMap<>();
        body.put("username", "admin");
        body.put("password", "admin");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
        JSONObject bodyJson = JSONObject.parseObject(responseEntity.getBody());
        assert bodyJson.getString("token") != null;
    }

    @Test
    public void login_other() {
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
        assert bodyJson.getString("token") != null;
    }
}
