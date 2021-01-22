package com.yang.order.server.controller;

import com.yang.order.server.dto.OrderDTO;
import com.yang.order.server.enums.ExecptionEnum;
import com.yang.order.server.execption.OrderExecption;
import com.yang.order.server.form.OrderForm;
import com.yang.order.server.service.OrderService;
import com.yang.order.server.util.OrderFormToOrderDTO;
import com.yang.order.server.util.ResultVoUtils;
import com.yang.order.server.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/14 15:07
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    //    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name="",value = "")
//    })
    @PostMapping("/create")
    public ResultVO create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确,OrderForm={}", orderForm);
            throw new OrderExecption(ExecptionEnum.PARAM_ERROR);
        }
        OrderDTO orderDTO = OrderFormToOrderDTO.getOrderDTO(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            throw new OrderExecption(ExecptionEnum.CAR_NUL);
        }
        OrderDTO result = orderService.create(orderDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("'成功");
        resultVO.setData(map);
        return resultVO;
    }

    @PostMapping("finish")
    public ResultVO<OrderDTO> finish(@RequestParam("orderId") String orderId) {
        return ResultVoUtils.success(orderService.finish(orderId));
    }

}
