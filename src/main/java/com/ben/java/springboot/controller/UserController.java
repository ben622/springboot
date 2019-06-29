package com.ben.java.springboot.controller;

import com.ben.java.springboot.bean.Result;
import com.ben.java.springboot.bean.TokenWrapper;
import com.ben.java.springboot.domain.UserInfo;
import com.ben.java.springboot.exception.LoginException;
import com.ben.java.springboot.repository.UserRepository;
import com.ben.java.springboot.util.ResultCode;
import com.ben.java.springboot.util.ResultFactory;
import com.ben.java.springboot.util.TokenManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserRepository repository;
    @Autowired
    TokenManager tokenManager;


    @RequestMapping(value = "/login")
    @ResponseBody
    public Result<UserInfo> login(HttpServletRequest request, @RequestParam("userId") String userId, @RequestParam("password") String passworde) throws LoginException {
        UserInfo userInfo = repository.findUserInfoByUidOrMobileAndPassword(Integer.parseInt(userId), userId, passworde);
        if (userInfo == null) {
            throw new LoginException();
        }

        TokenWrapper tokenWrapper = tokenManager.generateTokenByUser(userInfo);
        logger.info(tokenWrapper.getToken());

        request.getSession().setAttribute("user", userInfo);

        return ResultFactory.obtainResultBySuccessful(1, userInfo);
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/user/jumpUserManager")
    public String jumpUserManager(){
        return "user/userManager";
    }

}