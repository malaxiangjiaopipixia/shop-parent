package com.yang.order.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/17 11:35
 */
@RestController
@RefreshScope
public class EnvController {

    @Value("${env}")
    private String env;

    @RequestMapping("/hello")
    public String index(@RequestParam String name) {
        return name+","+this.env;
    }
}

