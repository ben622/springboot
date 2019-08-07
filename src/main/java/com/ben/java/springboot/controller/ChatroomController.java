package com.ben.java.springboot.controller;

import com.ben.java.springboot.bean.Result;
import com.ben.java.springboot.domain.ChatroomInfo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chatroom")
public class ChatroomController {


    @ResponseBody
    @RequestMapping("/createChatroom")
    public Result<ChatroomInfo> createChatroom(@Param("token") String token) {
        return null;
    }




}
