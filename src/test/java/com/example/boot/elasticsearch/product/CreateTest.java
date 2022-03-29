package com.example.boot.elasticsearch.product;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class CreateTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateTest.class);

    @Test
    public void importAll() {
        LOGGER.info("根据id新增商品到es");
        String url = String.format("http://localhost:8070/api/esProduct/create/%s", "1");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
    }
}
