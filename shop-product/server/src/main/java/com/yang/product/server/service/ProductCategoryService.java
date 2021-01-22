package com.yang.product.server.service;

import com.yang.product.server.entiry.ProductCategory;

import java.util.List;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/13 17:04
 */
public interface ProductCategoryService {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}
