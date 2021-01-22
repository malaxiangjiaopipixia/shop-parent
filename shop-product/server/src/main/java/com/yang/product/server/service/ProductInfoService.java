package com.yang.product.server.service;


import com.yang.product.common.DecreaseStockInput;
import com.yang.product.server.dto.CarDTO;
import com.yang.product.server.entiry.ProductInfo;

import java.util.List;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/13 15:51
 */
public interface ProductInfoService {

    /**
     *查询所有商品在架列表
     */
    List<ProductInfo> findUpAll();
    List<ProductInfo> listForOrder(List<String> list);

    void decreaseStock(List<DecreaseStockInput> decreaseStockInputList);
}
