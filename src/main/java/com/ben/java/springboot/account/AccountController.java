package com.ben.java.springboot.account;

import com.alibaba.druid.support.json.JSONParser;
import com.alibaba.fastjson.JSONArray;
import com.ben.java.springboot.account.service.AccountService;
import com.ben.java.springboot.bean.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@SessionAttributes("user")
public class AccountController {
    @Autowired
    AccountService accountService;
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("userName") String name, @RequestParam("password") String pwd){
        ModelAndView modelAndView = new ModelAndView("index");
        UserInfo userInfo = new UserInfo();
        userInfo.setNickname(name);
        userInfo.setPassword(pwd);
        modelAndView.addObject("user", userInfo);
        return modelAndView;
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @ModelAttribute("uuid")
    public String getUserUUID(){
        return UUID.randomUUID().toString();
    }


    @RequestMapping("/getUUID")
    public ModelAndView getUUID(@ModelAttribute("uuid") String uuid){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("user", uuid);
        return modelAndView;
    }


    @RequestMapping("queryAllUser")
    @ResponseBody
    public String queryAllUsers(){
        List<UserInfo> userInfos = accountService.findAll();
        return JSONArray.toJSONString(userInfos);
    }

}
