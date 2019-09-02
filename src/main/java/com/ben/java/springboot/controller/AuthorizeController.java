package com.ben.java.springboot.controller;

import com.ben.java.springboot.bean.Result;
import com.ben.java.springboot.domain.AuthorizeInfo;
import com.ben.java.springboot.domain.ChatroomInfo;
import com.ben.java.springboot.exception.ApiException;
import com.ben.java.springboot.repository.AuthorizeRepository;
import com.ben.java.springboot.util.ResultCode;
import com.ben.java.springboot.util.ResultFactory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * 授权管理
 */
@Controller
@RequestMapping("/chat/auth")
public class AuthorizeController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private AuthorizeRepository repository;

    /**
     * 验证客户端的accid是否合法
     * <p>
     * HTTP 2xx代码继续RTMP会话
     * HTTP 3xx将RTMP重定向到另一个流，其名称取自 LocationHTTP响应头。如果启动了新的流名称，rtmp:// 则会创建远程中继。中继要求指定IP地址而不是域名，并且仅适用于大于1.3.10的nginx版本。另见notify_relay_redirect。
     * 其他将丢弃RTMP连接
     *
     * @param accid
     * @return
     */
    @RequestMapping("/verifyAccid")
    public void verifyRTMP(HttpServletResponse response, @Param("accid") String accid) throws Exception {
        if (StringUtils.isBlank(accid)) {
            logger.info("accid verify failure，please check your accid");
            response.setStatus(500);
            response.getWriter().append("accid verify failure，please check your accid");
            return;
        }
        AuthorizeInfo authorizeInfo = repository.findAuthorizeInfoByAccid(accid);
        if (authorizeInfo == null) {
            logger.info("accid verify failure，please check your accid");
            response.setStatus(500);
            response.getWriter().append("accid verify failure，please check your accid");
            return;
        }


        logger.info("accid verify successful");
        response.setStatus(200);
        response.getWriter().append("accid verify successful");
    }


}
