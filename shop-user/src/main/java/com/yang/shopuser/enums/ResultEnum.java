package com.yang.shopuser.enums;

import lombok.Getter;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/15 10:23
 */
@Getter
public enum ResultEnum {

    LOGIN_FAIL(1,"登录失败"),
    ROLE_FAIL(2,"角色错误"),
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
