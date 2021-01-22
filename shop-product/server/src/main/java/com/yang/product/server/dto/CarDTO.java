package com.yang.product.server.dto;

import lombok.Getter;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/15 10:07
 */
@Getter
public class CarDTO {

    //产品id
    private String productId;
    //产品库存
    private Integer productStock;

    public CarDTO(String productId, Integer productStock) {
        this.productId = productId;
        this.productStock = productStock;
    }
}
