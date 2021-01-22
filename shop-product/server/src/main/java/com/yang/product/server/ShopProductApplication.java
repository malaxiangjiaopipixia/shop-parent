package com.yang.product.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author yang
 */
@SpringBootApplication
@EnableEurekaClient
public class ShopProductApplication {

    public static void main(String[] args) {

        SpringApplication.run(ShopProductApplication.class, args);
    }

}
