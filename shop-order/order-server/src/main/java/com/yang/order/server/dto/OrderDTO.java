package com.yang.order.server.dto;

import com.yang.order.server.entity.OrderDetail;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 14:09
 */
@Data
public class OrderDTO {
    //订单Id
    private String orderId;
    //买家姓名
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家唯一标识
    private String buyerOpenid;
    //订单金额
    private BigDecimal orderAmount;
    //订单状态
    private Integer orderStatus;
    //支付状态
    private Integer payStatus;

    private List<OrderDetail> orderDetailList;
}
