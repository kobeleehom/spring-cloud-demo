package com.dongxie.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigController {

    @Value("${version}")
    String version;

    @RequestMapping(value = "/getConfig")
    public String getConfig() {
        return version;
    }
}
