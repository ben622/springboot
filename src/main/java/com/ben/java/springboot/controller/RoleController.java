package com.ben.java.springboot.controller;

import com.ben.java.springboot.bean.Result;
import com.ben.java.springboot.domain.RoleInfo;
import com.ben.java.springboot.domain.UserInfo;
import com.ben.java.springboot.repository.RoleRepository;
import com.ben.java.springboot.util.ResultFactory;
import com.ben.java.springboot.util.TimeUtil;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/role")
public class RoleController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoleRepository repository;


    @RequestMapping(value = "/findByPage", method = RequestMethod.GET)
    @ResponseBody
    public Result<RoleInfo> findRoleByPage(@RequestParam("pageNum") int pageNum, @RequestParam("pageSize") int pageSize) {

        Result result = ResultFactory.obtainResultByPage(repository.findAll(PageRequest.of(pageNum - 1, pageSize,new Sort(Sort.Direction.DESC,"modifyTime"))));
        logger.info(result.toString());
        return result;

    }


    @RequestMapping(value = "/addRole", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> addRole(HttpServletRequest request, @RequestParam("roleName") String roleName, @RequestParam("roleRemark") String roleRemark) {
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setRoleName(roleName);
        roleInfo.setRoleRemark(roleRemark);
        roleInfo.setCreateTime(TimeUtil.DATE_FORMAT_YYYY_MM_DD.format(new Date()));
        roleInfo.setModifyTime(TimeUtil.DATE_FORMAT_YYYY_MM_DD.format(new Date()));
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        roleInfo.setUid(user.getUid());
        roleInfo.setUname(user.getNickName());

        RoleInfo saveRoleInfo = repository.save(roleInfo);
        logger.info(saveRoleInfo.toString());
        return ResultFactory.obtainResultBySuccessful(0, "成功");
    }

    @RequestMapping(value = "/delRole", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> delRole(@RequestParam("roleId") int roleId) {
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setRoleId(roleId);
        repository.delete(roleInfo);
        return ResultFactory.obtainResultBySuccessful(0, "删除角色成功");
    }


}
