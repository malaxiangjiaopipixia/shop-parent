package com.yang.product.server.utils.redis;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.PipelineBase;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author yby
 * @version 1.0
 * @date 2021/1/20 9:10
 */
@Service
public class StringRedisHelper extends StringRedisTemplate {

    private PipedJedisCluster pipedJedisCluster;

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @PostConstruct

    public void init() {
        setConnectionFactory(jedisConnectionFactory);
        afterPropertiesSet();
    }

    public byte[] getByte(String key) {
        return pipedJedisCluster.get(key.getBytes());
    }

    public void setByte(String key, byte[] value) {
        pipedJedisCluster.set(key.getBytes(), value);
    }

    private Map<String, String> getBatch(List<String> keys) {
        return pipedJedisCluster.doInPipeline(keys, Pipeline::get);
    }

    public Map<String, String> getByPattern(String pattern) {
        Set<String> keys = keys(pattern);
        Preconditions.checkNotNull(keys, "pattern have null keys!");

        List<String> ks = new ArrayList<>(keys);
        return getBatch(ks);
    }

    private Map<String, Map<String, String>> hGetBatch(List<String> keys) {
        Preconditions.checkNotNull(keys, "hGetAll keys null!");
        return pipedJedisCluster.doInPipeline(keys, PipelineBase::hgetAll);
    }

    public Map<String, Map<String, String>> hGetBatch(String[] keys) {
        Preconditions.checkNotNull(keys, "hGetAll keys null!");
        List<String> tmpKeys = new ArrayList<>(keys.length);
        Collections.addAll(tmpKeys, keys);
        return hGetBatch(tmpKeys);

    }
}

