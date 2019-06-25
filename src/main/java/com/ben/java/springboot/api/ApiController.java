package com.ben.java.springboot.api;

import com.alibaba.fastjson.JSONArray;
import com.ben.java.springboot.account.service.AccountService;
import com.ben.java.springboot.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class ApiController {
    @Autowired
    AccountService accountService;

    @RequestMapping("/getUsers")
    @ResponseBody
    public String queryAllUsers(){
        List<UserInfo> userInfos = accountService.findAll();
        return JSONArray.toJSONString(userInfos);
    }

}

