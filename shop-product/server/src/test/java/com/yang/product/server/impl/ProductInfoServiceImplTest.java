package com.yang.product.server.impl;

import com.yang.product.server.service.ProductInfoService;
import com.yang.product.server.entiry.ProductInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/13 16:22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductInfoServiceImplTest {
    @Autowired
    private ProductInfoService productInfoService;

    @Test
    void findUpAll() {
        List<ProductInfo> list = productInfoService.findUpAll();
        Assert.assertTrue(list.size()>0);
    }
}