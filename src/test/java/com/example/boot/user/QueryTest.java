package com.example.boot.user;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class QueryTest {
    @Test
    public void listAllUser() {
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
        String url = "http://localhost:8070/api/users/list?keyword=don&page=1&perPage=10";
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(null, null);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        assert responseEntity.getStatusCode().is2xxSuccessful();
        System.out.println(responseEntity.getBody());
    }
}
