package com.yang.product.server.utils.redis;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yby
 * @version 1.0
 * @date 2021/1/21 18:19
 */
@Component
@Slf4j
@RefreshScope
public class JedisPipeUtil {

    @Value("${lcloud.redis.nodes}")
    private String redisNodes;

    @Value("${lcloud.redis.password}")
    private String password;

    @Value("${lcloud.redis.maxIdle:10}")
    private Integer maxIdle;

    @Value("${lcloud.redis.maxTotal:200}")
    private Integer maxTotal;

    @Value("${lcloud.redis.minIdle:5}")
    private Integer minIdle;

    @Value("${lcloud.redis.maxWaitMillis:2000}")
    private Integer maxWaitMillis;

    @Value("${lcloud.redis.BlockWhenExhausted:true}")
    private Boolean blockWhenExhausted;

    @Value("${lcloud.redis.timeBetweenEvictionRunsMillis:60000}")
    private Long timeBetweenEvictionRunsMillis;

    @Value("${lcloud.redis.minEvictableIdleTimeMillis:60000}")
    private Long minEvictableIdleTimeMillis;

    @Value("${lcloud.redis.numTestsPerEvictionRun:5}")
    private Integer numTestsPerEvictionRun;

    @Value("${lcloud.redis.connectionTimeout:2000}")
    private Integer connectionTimeout;

    @Value("${lcloud.redis.soTimeout:2000}")
    private Integer soTimeout;

    @Value("${lcloud.redis.maxRedirects:1}")
    private Integer maxRedirects;

    @Bean
    public PipedJedisCluster pipedJedisCluster() {
        Set<HostAndPort> nodes = new HashSet<>(4);
        String[] nodeStrs = redisNodes.split(",");
        for (String nodeStr : nodeStrs) {
            String[] nodeConf = nodeStr.split(":");
            String host = nodeConf[0];
            int post = Integer.parseInt(nodeConf[1]);
            HostAndPort hostAndPort = new HostAndPort(host, post);
            nodes.add(hostAndPort);
        }

        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);
        config.setMinIdle(minIdle);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setBlockWhenExhausted(blockWhenExhausted);
        config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);

        return new PipedJedisCluster(nodes, connectionTimeout, soTimeout, maxRedirects, password, config);
    }

}
