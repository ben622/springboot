package com.ben.java.springboot.util;

public interface JedisOperations<K, V> {
    V get(K key);

    void set(K key, V value);
}