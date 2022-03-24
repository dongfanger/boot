package com.example.boot.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class QueryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryTest.class);

    @Test
    public void listAllUser() {
        LOGGER.info("查询所有用户");
        String url = "http://localhost:8070/api/users/listAll";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        JSONArray result = JSONObject.parseArray(responseEntity.getBody());
        assert result != null;
        for (Object r : result) {
            System.out.println(r);
        }
    }

    @Test
    public void listUserByPage() {
        LOGGER.info("分页查询用户");
        String url = "http://localhost:8070/api/users/list?keyword=don&page=1&perPage=10";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
    }

    @Test
    public void getUser() {
        LOGGER.info("查询单个用户");
        String url = "http://localhost:8070/api/users/1";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
    }
}
