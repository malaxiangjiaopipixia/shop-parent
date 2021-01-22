package com.yang.order.server.repository;

import com.yang.order.server.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 11:15
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    List<OrderDetail> findByOrderId(String orderId);
}
