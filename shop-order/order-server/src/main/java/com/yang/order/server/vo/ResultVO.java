package com.yang.order.server.vo;

import lombok.Data;

import java.util.List;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 16:49
 */
@Data
public class ResultVO<T> {

    private Integer code;

    private String msg;

    private T data;
}
