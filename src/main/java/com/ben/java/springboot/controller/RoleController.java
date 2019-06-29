package com.ben.java.springboot.controller;

import com.ben.java.springboot.bean.Result;
import com.ben.java.springboot.domain.RoleInfo;
import com.ben.java.springboot.repository.RoleRepository;
import com.ben.java.springboot.util.ResultFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoleRepository repository;

    @RequestMapping(value = "/findByPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<RoleInfo> findRoleByPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {
        Result result = ResultFactory.obtainResultByPage(repository.findAll(PageRequest.of(pageNum - 1, pageSize)));
        logger.info(result.toString());
        return result;

    }

    @RequestMapping("/jumpRoleManager")
    public String jumpRoleManager() {
        return "role/roleManager";
    }

}
