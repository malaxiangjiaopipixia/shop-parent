package com.yang.product.server.enums;


import lombok.Getter;

/**
 * 商品上下架状态
 * @author yby
 * @version 1.0
 * @date 2020/4/13 16:04
 */
@Getter
public enum ProductInfoEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;

    private Integer code;
    private String status;


    ProductInfoEnum(Integer code, String status) {
        this.code = code;
        this.status = status;
    }
}
