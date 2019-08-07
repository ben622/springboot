package com.ben.java.springboot.util;

import com.ben.java.springboot.bean.TokenWrapper;
import org.apache.commons.lang3.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;
import redis.clients.util.SafeEncoder;

import java.io.IOException;
import java.io.Serializable;

@Component
public class JedisTemplate  {
    @Autowired
    private RedisConfigure redisConfigure;

    public String get(String key) {
        JedisCluster jedisCluster = redisConfigure.getJedisCluster();
        String res = jedisCluster.get(key);
        try {
            jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
    public byte[] get(byte[] key) {
        JedisCluster jedisCluster = redisConfigure.getJedisCluster();
        byte[] res = jedisCluster.get(key);
        try {
            jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public void set(String key, Serializable value) {
        JedisCluster jedisCluster = redisConfigure.getJedisCluster();
        jedisCluster.set(key.getBytes(), SerializationUtils.serialize(value));
        try {
            jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void del(String key){
        JedisCluster jedisCluster = redisConfigure.getJedisCluster();
        jedisCluster.del(key.getBytes());
        try {
            jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
