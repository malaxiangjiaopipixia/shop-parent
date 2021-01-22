package com.yang.order.server.message;

import com.yang.order.server.dto.OrderDTO;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/22 18:31
 */
@Component
@EnableBinding(StreamClient.class)
public class StreamReceiver {

//    @StreamListener(StreamClient.INPUT)
//    public void getRabMsg(Object message){
//        System.out.println("Rabbit Message:"+message);
//    }

    @StreamListener(StreamClient.INPUT)
    public void getRabMsg(OrderDTO message){
        System.out.println("Rabbit Message:"+message);
    }
}
