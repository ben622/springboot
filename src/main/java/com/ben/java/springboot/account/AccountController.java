package com.ben.java.springboot.account;

import com.ben.java.springboot.bean.Response;
import com.ben.java.springboot.domain.UserInfo;
import com.ben.java.springboot.exception.LoginException;
import com.ben.java.springboot.repository.UserRepository;
import com.ben.java.springboot.util.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@SessionAttributes("user")
public class AccountController {
    @Autowired
    UserRepository repository;

    @RequestMapping("/login")
    @ResponseBody
    public Response<UserInfo> login(HttpServletRequest request, @RequestParam("userId") String userId, @RequestParam("password") String passworde) throws LoginException {
        UserInfo userInfo = repository.findUserInfoByUidOrMobileAndPassword(Integer.parseInt(userId), userId, passworde);
        if (userInfo == null) {
            throw new LoginException();
        }

        request.getSession().setAttribute("user", userInfo);

        Response<UserInfo> response = new Response<>();
        response.setCode(ResultCode.REQUEST_SUCCESSFUL);
        response.setMsg("成功");
        response.setData(userInfo);

        return response;
    }
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

}
