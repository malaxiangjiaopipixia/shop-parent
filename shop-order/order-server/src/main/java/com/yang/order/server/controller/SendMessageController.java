package com.yang.order.server.controller;

import com.yang.order.server.dto.OrderDTO;
import com.yang.order.server.message.StreamClient;
import com.yang.order.server.message.StreamReceiver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/22 18:30
 */
@RestController
public class SendMessageController {

    @Autowired
    private StreamClient streamClient;

//    @GetMapping("/sendMeaage")
//    public void sendMessage(){
//        streamClient.output().send(MessageBuilder.withPayload("now"+new Date()).build());
//    }

    @GetMapping("/sendMessage")
    public void sendMessage(){
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId("123");
        streamClient.output().send(MessageBuilder.withPayload(orderDTO).build());
    }
}
