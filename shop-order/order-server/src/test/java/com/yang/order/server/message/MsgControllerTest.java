package com.yang.order.server.message;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/17 15:22
 */
@Component
@RunWith(SpringRunner.class)
@SpringBootTest
class MsgControllerTest {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Test
    public void sendLogicMsg() {
        amqpTemplate.convertAndSend("myOrder","logic",new Date());
    }

    @Test
    public void sendFruitMsg() {
        amqpTemplate.convertAndSend("myOrder","fruit",new Date());
    }
}