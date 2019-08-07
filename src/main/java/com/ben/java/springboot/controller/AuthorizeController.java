package com.ben.java.springboot.controller;

import com.ben.java.springboot.bean.Result;
import com.ben.java.springboot.domain.AuthorizeInfo;
import com.ben.java.springboot.domain.ChatroomInfo;
import com.ben.java.springboot.exception.ApiException;
import com.ben.java.springboot.repository.AuthorizeRepository;
import com.ben.java.springboot.util.ResultCode;
import com.ben.java.springboot.util.ResultFactory;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 授权管理
 */
@Controller
@RequestMapping("/chat/auth")
public class AuthorizeController {

    @Autowired
    private AuthorizeRepository repository;

    /**
     * 验证客户端的accid是否合法
     *
     * @param accid
     * @return
     */
    @ResponseBody
    @RequestMapping("/verifyAccid")
    public Result<ChatroomInfo> createChatroom(@Param("accid") String accid) throws ApiException {
        if (StringUtils.isBlank(accid)) {
            throw new ApiException(ResultCode.ACCID_VERIFY_FAILURE, "accid verify failure，please check your accid");
        }
        AuthorizeInfo authorizeInfo = repository.findAuthorizeInfoByAccid(accid);

        if (authorizeInfo == null) {
            return ResultFactory.obtainResult(500, "accid verify failure，please check your accid", 0, accid);
        }

        return ResultFactory.obtainResult(200, "accid verify successful", 1, accid);
    }


}
