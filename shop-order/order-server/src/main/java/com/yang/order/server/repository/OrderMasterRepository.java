package com.yang.order.server.repository;

import com.yang.order.server.entity.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 11:14
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
}
