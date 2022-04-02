package com.example.boot.mongodb.readhistory;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class CreateTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateTest.class);

    @Test
    public void create() {
        LOGGER.info("创建浏览记录");
        String url = "http://localhost:8070/api/member/readHistory/create";
        RestTemplate restTemplate = new RestTemplate();
        HashMap<String, Object> body = new HashMap<>();
        body.put("memberId", "2");
        body.put("memberNickname", "dongfanger");
        body.put("productId", "3");
        body.put("productName", "江小白白酒150ml");
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
}
