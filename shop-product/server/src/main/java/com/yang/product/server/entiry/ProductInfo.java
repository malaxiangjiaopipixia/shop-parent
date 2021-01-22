package com.yang.product.server.entiry;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/10 17:50
 */
@Data
@Entity
public class ProductInfo {
    //商品ID
    @Id
    private String productId;
    //商品名
    private String productName;
    //商品价格
    private BigDecimal productPrice;
    //商品库存
    private Integer productStock;
    //商品描述
    private String productDescription;
    //商品图标
    private String productIcon;
    //商品状态
    private Integer productStatus;
    //商品类别
    private Integer categoryType;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

}
