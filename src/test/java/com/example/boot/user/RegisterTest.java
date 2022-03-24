package com.example.boot.user;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

import org.apache.commons.lang3.RandomStringUtils;


public class RegisterTest {
    @Test
    public void register() {
        String username = RandomStringUtils.randomAlphabetic(6);
        String url = "http://localhost:8070/api/users";
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> body = new HashMap<>();
        body.put("username", username);
        body.put("password", "qa123456");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
        JSONObject bodyJson = JSONObject.parseObject(responseEntity.getBody());
        assert username.equals(bodyJson.getString("username"));
    }
}
