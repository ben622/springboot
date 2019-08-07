package com.ben.java.springboot.controller;

import com.ben.java.springboot.bean.Result;
import com.ben.java.springboot.bean.TokenWrapper;
import com.ben.java.springboot.domain.Permission;
import com.ben.java.springboot.domain.RoleInfo;
import com.ben.java.springboot.domain.UserInfo;
import com.ben.java.springboot.domain.UserLoginLog;
import com.ben.java.springboot.exception.LoginException;
import com.ben.java.springboot.repository.LoginLogRepository;
import com.ben.java.springboot.repository.PermissionRepository;
import com.ben.java.springboot.repository.UserRepository;
import com.ben.java.springboot.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.regex.Pattern;

@Controller
@SessionAttributes("user")
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserRepository userRepository;
    @Autowired
    LoginLogRepository loginLogRepository;
    @Autowired
    PermissionRepository permissionRepository;
    @Autowired
    TokenManager tokenManager;
    @Autowired
    private StringRedisTemplate redis;

    /**
     * Ajax登陆
     *
     * @param request
     * @param userId
     * @param passworde
     * @return
     * @throws LoginException
     */
    @RequestMapping("/login")
    @ResponseBody
    public Result<UserInfo> login(HttpServletRequest request, @RequestParam("userId") String userId, @RequestParam("password") String passworde) throws LoginException {
        Pattern compile = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");
        UserInfo userInfo;
        if (compile.matcher(userId).matches()) {
            userInfo = userRepository.findUserInfoByMobileAndPassword(userId, passworde);
        } else {
            userInfo = userRepository.findUserInfoByUserIdAndPassword(userId, passworde);
        }
        if (userInfo == null) {
            throw new LoginException();
        }

        //从redis中获取一个token
        TokenWrapper tokenWrapper = tokenManager.generateToken(userInfo);
        request.getSession().setAttribute("user", tokenWrapper.getUserInfo());
        //登录成功，刷新该用户在数据库中的相关字段
        updateCurrLoginUser(request, tokenWrapper);

        return ResultFactory.obtainResultBySuccessful(1, userInfo);
    }

    private void updateCurrLoginUser(HttpServletRequest request, TokenWrapper tokenWrapper) {
        //刷新最后一次登录状态
        tokenWrapper.getUserInfo().setLastTime(TimeUtil.DATE_FORMAT_YYYY_MM_DD.format(new Date()));
        userRepository.save(tokenWrapper.getUserInfo());
        //记录登录信息
        UserLoginLog loginLog = new UserLoginLog();
        loginLog.setUid(tokenWrapper.getUserInfo().getUid());
        loginLog.setIP(IPUtil.getUserIP(request));
        loginLog.setToken(tokenWrapper.getToken());
        loginLog.setLoginDevice(request.getHeader("User-Agent"));
        loginLog.setLoginTime(TimeUtil.DATE_FORMAT_YYYY_MM_DD.format(new Date()));
        loginLogRepository.save(loginLog);

    }

    @RequestMapping("/loginByToken")
    @ResponseBody
    public Result<UserInfo> loginByToken(HttpServletRequest request, @RequestParam("token") String token) throws LoginException {
        TokenWrapper tokenWrapper = tokenManager.getTokenWrapperByToken(token);
        if (tokenWrapper == null) {
            throw new LoginException(ResultCode.ACCOUNT_ERROR_LOGIN_TOKEN_EMTPY, "登录状态失效");
        }
        //登录成功，刷新该用户在数据库中的相关字段
        updateCurrLoginUser(request, tokenWrapper);
        return ResultFactory.obtainResultBySuccessful(1, tokenWrapper.getUserInfo());
    }

    /**
     * 刷新token
     *
     * @param request
     * @param token
     * @return
     */
    @RequestMapping("/refreshToken")
    @ResponseBody
    public Result<String> refreshToken(HttpServletRequest request, @RequestParam("token") String token) throws LoginException {
        TokenWrapper tokenWrapper = tokenManager.refreshTokenWrapperByToken(token);
        if (tokenWrapper == null) {
            throw new LoginException(ResultCode.ACCOUNT_ERROR_LOGIN_TOKEN_EMTPY, "登录状态失效");
        }
        //刷新成功，刷新该用户在数据库中的相关字段
        updateCurrLoginUser(request, tokenWrapper);
        return ResultFactory.obtainResultBySuccessful(1, tokenWrapper.getUserInfo());
    }

    /**
     * 验证Token是否合法
     * @param token
     * @return
     * @throws LoginException
     */
    @RequestMapping("/verifyToken")
    @ResponseBody
    public Result<Boolean> verifyToken(@RequestParam("token") String token){
        TokenWrapper tokenWrapper = tokenManager.getTokenWrapperByToken(token);
        Result result = ResultFactory.obtainResultBySuccessful(1, tokenWrapper != null);
        logger.info(result.toString());
        return result;
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        return "login";
    }


    /**
     * 跳转登陆
     *
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin() {
        return "login";
    }


    @RequestMapping(value = "/user/findByPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<UserInfo> findRoleByPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Result result = ResultFactory.obtainResultByPage(userRepository.findAll(PageRequest.of(pageNum - 1, pageSize, new Sort(Sort.Direction.DESC, "lastTime"))));
        logger.info(result.toString());
        return result;

    }


    /**
     * 获取用户的权限
     *
     * @param uid
     * @return
     */
    @RequestMapping(value = "/user/getUserPermission", method = RequestMethod.GET)
    @ResponseBody
    public Result<Permission> findPermissionByUid(@RequestParam("uid") int uid) {
        Result result = ResultFactory.obtainResultByList(permissionRepository.findAllByUid(uid));
        logger.info(result.toString());
        return result;

    }


}
