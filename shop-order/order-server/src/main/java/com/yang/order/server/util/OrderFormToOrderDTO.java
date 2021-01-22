package com.yang.order.server.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yang.order.server.entity.OrderDetail;
import com.yang.order.server.dto.OrderDTO;
import com.yang.order.server.enums.ExecptionEnum;
import com.yang.order.server.execption.OrderExecption;
import com.yang.order.server.form.OrderForm;

import java.util.List;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 16:03
 */
public class OrderFormToOrderDTO {

    public static OrderDTO getOrderDTO(OrderForm orderForm){
        Gson gson = new Gson();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());
        orderDTO.setBuyerPhone(orderForm.getPhone());

        List<OrderDetail> orderDetailList;
        try{
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        }catch(Exception e){
            e.printStackTrace();
            throw new OrderExecption(ExecptionEnum.CONVERT_ERROR);
        }

        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
