package com.dongxie.consumefeign.service;

import com.dongxie.consumefeign.service.hystric.ServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 服务层其实是一个 feign  的客户端，以http的形式调用其他服务对应的方法
 */
@FeignClient(value = "SERVER-HELLO", fallback = ServiceHystric.class)
public interface HelloService {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String getHello(@RequestParam(value = "name") String name);
}
