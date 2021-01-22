package com.yang.order.server.enums;

import lombok.Getter;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 11:54
 */
@Getter
public enum PayStatusEnum {
    WAIT(0,"待支付"),
    SUCCESS(1,"已支付"),
    ;

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
