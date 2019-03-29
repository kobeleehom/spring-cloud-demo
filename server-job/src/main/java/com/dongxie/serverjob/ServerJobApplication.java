package com.dongxie.serverjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServerJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerJobApplication.class, args);
    }

}
