package com.yang.product.server.repository;

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
 * @date 2020/4/10 18:12
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    void findByProductStatus() {
        List<ProductInfo> list = productInfoRepository.findByProductStatus(0);
//        List<ProductInfo> list1 = productInfoRepository.findByCreateTimeBetween(2017-08-01);
        Assert.assertTrue(list.size()>0);
    }
}