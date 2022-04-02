package com.example.boot.mongodb.readhistory;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class ListTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListTest.class);

    @Test
    public void list() {
        LOGGER.info("展示浏览记录");
        String url = "http://localhost:8070/api/member/readHistory/list?memberId=2";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        try {
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
            System.out.println(responseEntity.getBody());
        } catch (HttpServerErrorException e) {
            System.out.println(e.getMessage());
        }
    }
}
