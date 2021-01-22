package com.yang.shopuser.VO;

import lombok.Data;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/13 17:14
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String message;

    private T data;
}
