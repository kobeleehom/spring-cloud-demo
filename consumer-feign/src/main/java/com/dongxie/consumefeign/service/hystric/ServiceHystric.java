package com.dongxie.consumefeign.service.hystric;

import com.dongxie.consumefeign.service.HelloService;
import org.springframework.stereotype.Component;

@Component
public class ServiceHystric implements HelloService {
    @Override
    public String getHello(String name) {
        return "hello is error, sorry " + name;
    }
}
