package com.example.boot.user;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

public class AuthTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthTest.class);

    @Test
    public void checkAuth() {
        LOGGER.info("验证授权--无权限返回403");
        String url = "http://localhost:8070/api/users/checkAuth";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        try {
            restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        } catch (HttpClientErrorException e) {
            assert Objects.requireNonNull(e.getMessage()).contains("403");
        }
    }

}
