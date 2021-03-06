package com.yang.shopgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ShopGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopGatewayApplication.class, args);
    }

    @ConfigurationProperties("zuul")
    @RefreshScope
    private ZuulProperties zuulProperties(){
        return new ZuulProperties();
    }
}
