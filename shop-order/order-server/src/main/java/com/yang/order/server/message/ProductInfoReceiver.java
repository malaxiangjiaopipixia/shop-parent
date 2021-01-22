package com.yang.order.server.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yang.order.server.util.JsonUtil;
import com.yang.product.common.ProductInfoOutput;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yby
 * @version 1.0
 * @date 2020/4/23 11:44
 */
@Component
public class ProductInfoReceiver {
    private static final String PRODUCT_STOCK = "product_stock_%s";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void getProductionInfoMsg(String message) {
        List<ProductInfoOutput> productInfoOutputs = (List<ProductInfoOutput>) JsonUtil.fromJson1(message,
                new TypeReference<List<ProductInfoOutput>>() {});
        for(ProductInfoOutput productInfoOutput:productInfoOutputs){
            //存储到redis
            stringRedisTemplate.opsForValue().set(String.format(PRODUCT_STOCK,productInfoOutput.getProductId()),
                    String.valueOf(productInfoOutput.getProductStock()));
        }
    }
}
