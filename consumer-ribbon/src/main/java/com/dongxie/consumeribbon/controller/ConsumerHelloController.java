package com.dongxie.consumeribbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dongxie.consumeribbon.service.HelloService;

@RestController
public class ConsumerHelloController {

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/getHello")
    public String getHello(@RequestParam String name) {
        return helloService.getHello(name);
    }
}
