package com.dongxie.serverhello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final static Logger logger = LoggerFactory.getLogger(HelloController.class);

    @Value("${server.port}")
    String port;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "test") String name) {
        logger.info("server hello in ...");
        return "hello " + name + ", my server port is :" + port;
    }
}
