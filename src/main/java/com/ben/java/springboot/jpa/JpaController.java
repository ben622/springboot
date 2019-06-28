package com.ben.java.springboot.jpa;

import com.alibaba.fastjson.JSON;
import com.ben.java.springboot.jpa.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/v1/jpa")
public class JpaController {

    @Autowired
    private AccountRepository repository;

    @RequestMapping("/getUserInfo/{uid}")
    @ResponseBody
    public String getUserInfo(@PathVariable("uid") int uid) {
        return JSON.toJSONString(repository.findUserInfoByUid(uid));
    }

}
