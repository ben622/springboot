package com.ben.java.springboot.util;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class RedisConfigure {

    @Value("${spring.redis.cluster.nodes}")
    private String nodes;

    @Value("${spring.redis.password}")
    private String password;

    /**
     * 连接池在给定时间可以分配的最大连接数。
     */
    @Value("${spring.redis.jedis.pool.max-active}")
    private int poolMaxActive;

    /**
     * 连接池中“空闲”连接的最大数量
     */
    @Value("${spring.redis.jedis.pool.max-idle}")
    private int poolMaxIdle;

    /**
     * 连接池中维护的最小空闲连接数
     */
    @Value("${spring.redis.jedis.pool.min-idle}")
    private int poolMinIdle;

    /**
     * 连接池在给定时间可以分配的最大连接数
     */
    @Value("${spring.redis.lettuce.pool.max-active}")
    private int lettucePoolMaxActive;

    /**
     * 连接池中“空闲”连接的最大数量
     */
    @Value("${spring.redis.lettuce.pool.max-idle}")
    private int lettucePoolMaxIdle;


    /**
     * 连接池池耗尽时，在抛出异常之前连接分配应阻塞的最长时间
     */
    @Value("${spring.redis.lettuce.pool.max-wait}")
    private Duration lettucePoolMaxWait;

    /**
     * 连接池中维护的最小空闲连接数。此设置仅在其为正时才有效。
     */
    @Value("${spring.redis.lettuce.pool.min-idle}")
    private int lettucePoolMinIdle;


    @Value("${spring.redis.timeout}")
    private int connectionTimeout;

    public JedisCluster getJedisCluster() {
        Set<HostAndPort> nodes = new HashSet<>();
        for (String element : this.nodes.split(",")) {
            String[] node = element.split(":");
            nodes.add(new HostAndPort(node[0], Integer.parseInt(node[1])));
        }
        GenericObjectPoolConfig jedisPoolConfig = new GenericObjectPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(poolMaxIdle);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(poolMaxActive);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(poolMinIdle);
        jedisPoolConfig.setMaxWaitMillis(connectionTimeout);
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);
        return new JedisCluster(nodes, connectionTimeout, connectionTimeout, connectionTimeout, password, jedisPoolConfig);
    }


}

