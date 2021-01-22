package com.yang.order.server.repository;

import com.yang.order.server.entity.OrderMaster;
import com.yang.order.server.enums.OrderStatusEnum;
import com.yang.order.server.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;


/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 11:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
 public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("杨");
        orderMaster.setBuyerAddress("重庆");
        orderMaster.setBuyerPhone("1990123445");
        orderMaster.setBuyerOpenid("111");
        orderMaster.setOrderAmount(new BigDecimal(5.21));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);
    }

}