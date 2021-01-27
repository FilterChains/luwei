package com.provider.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/test/{string}", method = RequestMethod.GET)
    public String test(@PathVariable String string) {
        return "Hello Nacos :" + string;
    }
}
