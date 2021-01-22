package com.yang.order.server.execption;

import com.yang.order.server.enums.ExecptionEnum;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 15:39
 */
public class OrderExecption extends RuntimeException {
    private Integer code;

    public OrderExecption(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public OrderExecption(ExecptionEnum execptionEnum){
        super(execptionEnum.getMessage());
        this.code = execptionEnum.getCode();
    }
}
