package com.example.boot.user;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class UpdateTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTest.class);
    Map<String, Object> vars = new HashMap<>();

    @BeforeEach
    public void create() {
        LOGGER.info("新增");
        String username = RandomStringUtils.randomAlphabetic(6);
        vars.put("username", username);
        String url = "http://localhost:8070/api/users";
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> body = new HashMap<>();
        body.put("username", username);
        body.put("password", "qa123456");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        System.out.println(responseEntity.getBody());

        LOGGER.info("获取id");
        JSONObject bodyJson = JSONObject.parseObject(responseEntity.getBody());
        Long id = bodyJson.getLong("id");
        vars.put("id", id);
        System.out.println(id);
    }

    @Test
    public void update() {
        LOGGER.info("更新");
        String url = String.format("http://localhost:8070/api/users/%d", vars.get("id"));
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> body = new HashMap<>();
        body.put("username", vars.get("username") + "_new");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
        System.out.println(responseEntity.getBody());
        JSONObject bodyJson = JSONObject.parseObject(responseEntity.getBody());
        Long id = bodyJson.getLong("id");
        assert id != null;
    }
}
