package com.yang.product.test;

import redis.clients.jedis.Pipeline;
import redis.clients.jedis.PipelineBase;

import java.util.function.BiConsumer;

/**
 * @author yby
 * @version 1.0
 * @date 2021/1/21 16:55
 */
public class Test0121A {
    public static void main(String[] args) {
        IConvert<String, String> convert = Test0121::startWith;
        String result = convert.convert("abc");
        System.out.println("result:" + result);


        BiConsumer<Pipeline,String> aa = PipelineBase::hgetAll;


    }
}
