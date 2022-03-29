package com.example.boot.elasticsearch.product;

import com.example.boot.redis.authcode.AuthCodeCheckTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class SearchTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SearchTest.class);

    @Test
    public void searchName() {
        LOGGER.info("根据商品名称搜索");
        String url = String.format("http://localhost:8070/api/esProduct/search/simple?keyword=%s", "白酒");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
    }

    @Test
    public void searchSubTitle() {
        LOGGER.info("根据商品名称搜索");
        String url = String.format("http://localhost:8070/api/esProduct/search/simple?keyword=%s", "好喝");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
    }
}
