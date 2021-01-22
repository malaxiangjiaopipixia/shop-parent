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
 * @date 2020/4/14 9:09
 */
@Data
@Entity
public class OrderDetail {

    @Id
    private String detailId;
    //订单Id
    private String orderId;
    //商品Id
    private String productId;
    //商品名称
    private String productName;
    //商品价格
    private BigDecimal productPrice;
    //商品数量
    private Integer productQuantity;
    //商品图标
    private String productIcon;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

}
