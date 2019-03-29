package com.dongxie.serverjob.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private final static Logger logger = LoggerFactory.getLogger(JobController.class);

    @Value("${server.port}")
    String port;

    @RequestMapping("/desc")
    public String getServerDesc() {
        logger.info("server job in desc...");
        return "I am job server desc, my server port is :" + port;
    }

}
