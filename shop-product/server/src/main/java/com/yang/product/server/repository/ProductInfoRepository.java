package com.yang.product.server.repository;

import com.yang.product.server.entiry.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/10 18:07
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{
    List<ProductInfo> findByProductStatus(Integer productStatus);
    List<ProductInfo> findByProductIdIn(List<String> list);
    Optional<ProductInfo> findById(String id);
}

