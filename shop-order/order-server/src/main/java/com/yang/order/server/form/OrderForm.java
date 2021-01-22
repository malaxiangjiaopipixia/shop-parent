package com.yang.order.server.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 15:19
 */
@Data
public class OrderForm {

    @NotEmpty(message = "姓名必填")
    private String name;

    @NotEmpty(message = "手机号必填")
    private String phone;

    @NotEmpty(message = "地址必填")
    private String address;

    @NotEmpty(message = "微信用户id必填")
    private String openid;

    @NotEmpty(message = "微信用户id必填")
    private String items;
}
