package com.yang.product.server.execption;

import com.yang.product.server.enums.ResultEnum;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/15 10:18
 */
public class ResultExecuption extends RuntimeException {


    private Integer code;

    public ResultExecuption(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
