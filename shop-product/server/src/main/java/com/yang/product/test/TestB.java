package com.yang.product.test;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yby
 * @version 1.0
 * @date 2021/1/15 13:40
 */
@Slf4j
public class TestB {
    public static void main1(String[] args) {
        Jedis redis = new Jedis("124.71.16.155", 6379);
        redis.select(10);
        redis.flushDB();
        Map<String, String> data = new HashMap<>();

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            data.clear();
            data.put("k_" + i, "v_" + i);
            redis.hmset("key_" + i, data);
        }
        log.info("DB10 size:{}.", redis.dbSize());
        log.info("未使用pipeline的耗时：{}s.", (System.currentTimeMillis() - startTime) / 1000);

        redis.select(11);
        redis.flushDB();
        Pipeline pipeline = redis.pipelined();
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 500; j++) {
                data.clear();
                data.put("k_" + (i * 500 + j), "v_" + (i * 500 + j));
                pipeline.hmset("key_" + (i * 500 + j), data);
            }
            pipeline.sync();
        }


        log.info("DB11 size:{}.", redis.dbSize());
        log.info("使用pipeline的耗时：{}s.", (System.currentTimeMillis() - time1) / 1000);
    }
}
