package com.example.boot.user;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class PasswordResetTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordResetTest.class);

    @Test
    void resetPassword() {
        LOGGER.info("重置密码");
        String url = "http://localhost:8070/api/users/32/passwords/reset";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
        JSONObject bodyJson = JSONObject.parseObject(responseEntity.getBody());
        assert "qa123456".equals(bodyJson.getString("data"));
    }
}
