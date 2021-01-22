package com.yang.product.server.controller;

import com.yang.product.server.service.ProductInfoService;
import com.yang.product.server.entiry.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 23:19
 */
@RestController
public class ServerController {

    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping("/msg")
    public String msg(){
        return "ok";
    }

    @GetMapping("/getProductList")
    public List<ProductInfo> getProductList(List<String> list){
        return productInfoService.listForOrder(list);
    }




}
