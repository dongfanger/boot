package com.example.boot.elasticsearch.product;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class DeleteTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImportAllTest.class);

    @Test
    public void delete() {
        LOGGER.info("根据id删除");
        String url = String.format("http://localhost:8070/api/esProduct/delete/%s", "1");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
    }

    @Test
    public void batchDelete() {
        LOGGER.info("批量删除");
        String url = String.format("http://localhost:8070/api/esProduct/delete/batch?ids=%s", "1,2");
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<HashMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
    }
}
