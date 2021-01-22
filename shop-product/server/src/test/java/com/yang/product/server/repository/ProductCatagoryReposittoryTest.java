package com.yang.product.server.repository;

import com.yang.product.server.entiry.ProductCategory;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/13 15:38
 */
@RunWith(SpringRunner.class)
@SpringBootTest
class ProductCatagoryReposittoryTest {
    @Autowired
    private ProductCatagoryReposittory productCatagoryReposittory;

    @Test
    void findByCategoryTypeIn() {
        List<ProductCategory> list = productCatagoryReposittory.findByCategoryTypeIn(Arrays.asList(11,22));
        Assert.assertTrue(list.size()>0);
    }
}