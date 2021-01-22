package com.yang.product.server.service.impl;

import com.yang.product.server.entiry.ProductCategory;
import com.yang.product.server.repository.ProductCatagoryReposittory;
import com.yang.product.server.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/13 17:06
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private ProductCatagoryReposittory productCatagoryReposittory;
    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> list) {
        return productCatagoryReposittory.findByCategoryTypeIn(list);
    }
}
