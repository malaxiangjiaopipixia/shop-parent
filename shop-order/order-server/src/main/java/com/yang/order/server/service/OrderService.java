package com.yang.order.server.service;

import com.yang.order.server.dto.OrderDTO;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 14:03
 */
public interface OrderService {
    OrderDTO create(OrderDTO orderDTO);

    OrderDTO finish(String orderId);
}
