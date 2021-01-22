package com.yang.product.server.enums;

import lombok.Getter;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/15 10:23
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(1,"商品不存在"),

    PRODUCT_STOCK_NOT_ENOUGH(2,"商品库存不足"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
