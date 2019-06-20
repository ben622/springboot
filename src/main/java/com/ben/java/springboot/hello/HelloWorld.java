package com.ben.java.springboot.hello;

import org.springframework.stereotype.Component;

@Component
public class HelloWorld {
    public String hello() {
        return "hello spring boot!";
    }
}
