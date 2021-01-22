package com.yang.product.server.utils.redis;

import com.google.common.base.Preconditions;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.*;
import redis.clients.jedis.util.JedisClusterCRC16;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

/**
 * @author yby
 * @version 1.0
 * @date 2021/1/20 9:23
 */
public class PipedJedisCluster extends JedisCluster {

    private JedisClusterInfoCache cache;

    public PipedJedisCluster(Set<HostAndPort> jedisClusterNode,
                             int connectionTimeout,
                             int soTimeout,
                             int maxAttempts,
                             String password,
                             GenericObjectPoolConfig poolConfig) {
        super(jedisClusterNode, connectionTimeout, soTimeout, maxAttempts, password, poolConfig);

        MetaObject metaobject = SystemMetaObject.forObject(connectionHandler);
        this.cache = (JedisClusterInfoCache) metaobject.getValue("cache");

    }


    <T> Map<String, T> doInPipeline(List<String> keys, BiConsumer<Pipeline, String> keyConsumer) {
        Preconditions.checkNotNull(keyConsumer, "null key consumer");

        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptyMap();
        }

        Map<JedisPool, List<String>> connKeysMap = keys.stream().collect(Collectors.groupingBy(k -> {
            int slot = JedisClusterCRC16.getSlot(k);
            return cache.getSlotPool(slot);
        }));

        Map<String, T> resultMap = new HashMap<>(128);
        for (Map.Entry<JedisPool, List<String>> entry : connKeysMap.entrySet()) {
            JedisPool jedisPool = new JedisPool();
            List<String> grounpKeys = entry.getValue();

            List<T> result;
            try (Pipeline p = jedisPool.getResource().pipelined()) {
                grounpKeys.forEach(key -> keyConsumer.accept(p, key));
                result = (List<T>) p.syncAndReturnAll();
            }

            for (int i = 0; i < result.size(); ++i) {
                resultMap.put(grounpKeys.get(i), result.get(i));
            }
        }
        return resultMap;
    }

    List<Boolean> doInGetBitPipeline(String key, List<Integer> indexes) {
        if (StringUtils.isEmpty(key)) {
            return Collections.emptyList();
        }

        if (CollectionUtils.isEmpty(indexes)) {
            return Collections.emptyList();
        }

        int slot = JedisClusterCRC16.getCRC16(key);
        JedisPool jedisPool = cache.getSlotPool(slot);

        List results;
        try (Pipeline p = jedisPool.getResource().pipelined()) {
            for (Integer index : indexes) {
                p.getbit(key, index);
            }
            results = p.syncAndReturnAll();
        }
        return results;
    }

    List<Boolean> doInSetBitPipeline(String key, List<Integer> indexes) {
        if (StringUtils.isEmpty(key)) {
            return Collections.emptyList();
        }

        if (CollectionUtils.isEmpty(indexes)) {
            return Collections.emptyList();
        }

        int slot = JedisClusterCRC16.getCRC16(key);
        JedisPool jedisPool = cache.getSlotPool(slot);

        List results;
        try (Pipeline p = jedisPool.getResource().pipelined()) {
            for (Integer index : indexes) {
                p.setbit(key, index, true);
            }
            results = p.syncAndReturnAll();
        }
        return results;
    }

    Map<String, List<String>> listBatch(int rangeSize, List<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return Collections.emptyMap();
        }

        Map<JedisPool, List<String>> connKeyMap = keys.stream().collect(Collectors.groupingBy(k -> {
            int slot = JedisClusterCRC16.getSlot(k);
            return cache.getSlotPool(slot);
        }));

        Map<String, List<String>> resultMap = new HashMap<>(128);
        for (Map.Entry<JedisPool, List<String>> entry : connKeyMap.entrySet()) {
            JedisPool jedisPool = entry.getKey();
            List<String> grounpKeys = entry.getValue();

            List<Object> results;
            try (Pipeline p = jedisPool.getResource().pipelined()) {
                grounpKeys.forEach(key -> p.lrange(key, 0, rangeSize));
                results = p.syncAndReturnAll();
            }
            for (int i = 0; i < results.size(); i++) {
                if (results.get(i) == null) {
                    resultMap.put(grounpKeys.get(i), Collections.emptyList());
                } else {
                    resultMap.put(grounpKeys.get(i), (List<String>) results.get(i));
                }
            }
        }
        return resultMap;
    }

}
