package com.yang.order.server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 9:17
 */
@Data
@Entity
public class OrderMaster {

    @Id
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
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;



}
