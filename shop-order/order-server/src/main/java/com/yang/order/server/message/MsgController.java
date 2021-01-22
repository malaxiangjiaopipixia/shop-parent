package com.yang.order.server.message;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/17 15:18
 */
//@Component
public class MsgController {

//    @RabbitListener(queuesToDeclare = @Queue("myQueues"))
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("myQueue"),
//            exchange = @Exchange("myExchange")
//    ))
//    public void getRabMsg(String str){
//        System.out.println(
//                "Rabbit Message:"+str
//        );
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("logicQueue"),
//            key = "logic",
//            exchange = @Exchange("myOrder")
//    ))
//    public void getLogicMsg(String str){
//        System.out.println(
//                "logic Message:"+str
//        );
//    }
//
//    @RabbitListener(bindings = @QueueBinding(
//            value = @Queue("fruitQueue"),
//            key = "fruit",
//            exchange = @Exchange("myOrder")
//    ))
//    public void getFruitMsg(String str){
//        System.out.println(
//                "fruit Message:"+str
//        );
//    }
}
