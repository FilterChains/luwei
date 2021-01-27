package com.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class TestConrtroller {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("test/{string}")
    public String test(@PathVariable String string) {
        return restTemplate.getForObject("http://provider/test/" + string, String.class);
    }
}
