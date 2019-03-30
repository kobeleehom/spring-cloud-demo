package com.dongxie.consumefeign;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@EnableHystrixDashboard
public class ConsumeFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumeFeignApplication.class, args);
    }

    /**
     * 熔断追踪仪表盘 ： http://localhost:8883/hystrix
     * 这个地方日坑，用了springboot2.0和springcloud  Finchley版本
     * 必须加上这个servlet才可以  而且访问地址必须是下面这种形式
     * http://localhost:8883/actuator/hystrix.stream
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServletFeign");
        return registrationBean;
    }

}
