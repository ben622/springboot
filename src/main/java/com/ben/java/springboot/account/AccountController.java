package com.ben.java.springboot.account;

import com.ben.java.springboot.bean.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@SessionAttributes("user")
public class AccountController {
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("userName") String name, @RequestParam("password") String pwd){
        ModelAndView modelAndView = new ModelAndView("index");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(name);
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

}
