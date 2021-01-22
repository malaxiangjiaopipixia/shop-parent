package com.yang.product.server.repository;

import com.yang.product.server.entiry.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/13 15:32
 */
public interface ProductCatagoryReposittory extends JpaRepository<ProductCategory,Integer> {
    List<ProductCategory> findByCategoryTypeIn(List<Integer> list);
}
