package com.ben.java.springboot;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SpringbootApplicationTests {

    @Test
    public void contextLoads() {
     /*Jedis jedis = new Jedis("www.zhangchuany.com",20001);
        jedis.auth("redis622");
        System.out.println(jedis.clusterNodes());
        jedis.set("mykey", "hello redis");
        System.out.println(jedis.get("token"));
        jedis.close();*/

        Set<HostAndPort> nodes = new HashSet<>();
        //nodes.add(new HostAndPort("39.105.76.133", 20001));
        nodes.add(new HostAndPort("39.105.76.133", 20002));
        nodes.add(new HostAndPort("39.105.76.133", 20003));
        nodes.add(new HostAndPort("39.105.76.133", 20004));
        nodes.add(new HostAndPort("39.105.76.133", 20005));
        nodes.add(new HostAndPort("39.105.76.133", 20006));
        // Jedis连接池配置
        GenericObjectPoolConfig jedisPoolConfig = new GenericObjectPoolConfig();
        // 最大空闲连接数, 默认8个
        jedisPoolConfig.setMaxIdle(100);
        // 最大连接数, 默认8个
        jedisPoolConfig.setMaxTotal(500);
        //最小空闲连接数, 默认0
        jedisPoolConfig.setMinIdle(0);
        // 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
        jedisPoolConfig.setMaxWaitMillis(2000); // 设置2秒
        //对拿到的connection进行validateObject校验
        jedisPoolConfig.setTestOnBorrow(true);

        JedisCluster jedisCluster = new JedisCluster(nodes,10000,100000,10000,"",jedisPoolConfig);
        //edisCluster.set("token", "new token by cluster");
        System.out.println(jedisCluster.get("token"));

        try {
            jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
