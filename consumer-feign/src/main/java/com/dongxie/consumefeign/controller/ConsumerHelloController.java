package com.dongxie.consumefeign.controller;

import com.dongxie.consumefeign.service.HelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerHelloController {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerHelloController.class);

    @Autowired
    HelloService helloService;

    @RequestMapping(value = "/getHello")
    public String getHello(@RequestParam String name){
        logger.error("追踪..调用服务之前.feign..");
        return helloService.getHello(name);
    }
}
