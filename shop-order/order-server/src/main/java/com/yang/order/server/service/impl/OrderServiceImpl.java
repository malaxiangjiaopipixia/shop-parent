package com.yang.order.server.service.impl;

import com.yang.order.server.dto.OrderDTO;
import com.yang.order.server.entity.OrderDetail;
import com.yang.order.server.entity.OrderMaster;
import com.yang.order.server.enums.ExecptionEnum;
import com.yang.order.server.enums.OrderStatusEnum;
import com.yang.order.server.enums.PayStatusEnum;
import com.yang.order.server.execption.OrderExecption;
import com.yang.order.server.repository.OrderDetailRepository;
import com.yang.order.server.repository.OrderMasterRepository;
import com.yang.order.server.util.KeyUtils;
import com.yang.order.server.service.OrderService;
import com.yang.product.client.ProductClient;
import com.yang.product.common.DecreaseStockInput;
import com.yang.product.common.ProductInfoOutput;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 14:04
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private ProductClient productClient;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        String orderId = KeyUtils.getUniqueKey();
        orderDTO.setOrderId(orderId);
        //获取订单中商品详情
        List<String> productIdList = orderDTO.getOrderDetailList()
                .stream().map(OrderDetail::getProductId)
                .collect(Collectors.toList());

        List<ProductInfoOutput> productInfoList = productClient.listForOrder(productIdList);

        List<DecreaseStockInput> decreaseStockInputs = orderDTO.getOrderDetailList().stream()
                .map(e -> new DecreaseStockInput(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productClient.decreaseStock(decreaseStockInputs);

        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
        for (ProductInfoOutput productInfo : productInfoList) {
            for (OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
                if (orderDetail.getProductId().equals(productInfo.getProductId())) {
                    BeanUtils.copyProperties(productInfo, orderDetail);
                    orderDetail.setDetailId(KeyUtils.getUniqueKey());
                    orderDetail.setOrderId(orderDTO.getOrderId());
                    orderAmount = productInfo.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(orderAmount);
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);
        return orderDTO;
    }

    @Override
    @Transactional
    public OrderDTO finish(String orderId) {
        // 1. 查询订单
        Optional<OrderMaster> orderMasterOptional = orderMasterRepository.findById(orderId);
        if (!orderMasterOptional.isPresent()) {
            throw new OrderExecption(ExecptionEnum.ORDER_NOT_EXIST);
        }

        // 2. 获取订单状态
        OrderMaster orderMaster = orderMasterOptional.get();
        if (orderMaster.getOrderStatus() != OrderStatusEnum.NEW.getCode()) {
            throw new OrderExecption(ExecptionEnum.ORDER_STATUS_ERROR);
        }

        // 3. 完成订单
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderMaster.getOrderId());
        if (CollectionUtils.isEmpty(orderDetailList)) {
            throw new OrderExecption(ExecptionEnum.CAR_NUL);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }
}
