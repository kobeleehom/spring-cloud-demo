package com.dongxie.consumeribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    private static final Logger logger = LoggerFactory.getLogger(HelloService.class);


    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloError")
    public String getHello(String name) {
        logger.error("调用服务之前...");
        return restTemplate.getForObject("http://server-hello/hello?name="+name,String.class);
    }

    public String helloError(String name) {
        return "get hello is error , sorry " + name;
    }
}
