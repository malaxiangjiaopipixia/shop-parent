package com.yang.order.server.enums;

import lombok.Getter;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 15:43
 */
@Getter
public enum ExecptionEnum {
    PARAM_ERROR(1,"参数错误"),
    CONVERT_ERROR(2,"json转换错误"),
    CAR_NUL(3,"购物车为空"),
    ORDER_NOT_EXIST(4,"订单不存在"),
    ORDER_STATUS_ERROR(5,"订单状态错误")
    ;

    private Integer code;
    private String message;


    ExecptionEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


}
