package com.yang.shopgateway.enums;

import lombok.Getter;

/**
 * @author yby
 * @version 1.0
 * @date 2020/7/1 10:51
 */
@Getter
public enum RoleEnum {
    BUYER(1,"买家"),
    SALLER(2,"卖家"),
    ;

    private Integer code;
    private String msg;

    RoleEnum(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }


}
