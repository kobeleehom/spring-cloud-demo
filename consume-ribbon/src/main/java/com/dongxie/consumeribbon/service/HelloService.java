package com.dongxie.consumeribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    RestOperations restTemplate;

    public String getHello(String name) {
        return restTemplate.getForObject("http://SERVER-HELLO/hello?name=etasf",String.class);
    }
}
