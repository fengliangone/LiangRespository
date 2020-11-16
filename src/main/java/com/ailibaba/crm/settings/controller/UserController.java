package com.ailibaba.crm.settings.controller;

import com.ailibaba.crm.settings.bean.User;
import com.ailibaba.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.settings.controller
 * @Description: user
 * @Author: 亮哥
 * @CreateDate: 2020/11/16 17:00
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/quertUsers")
    @ResponseBody
    public List<User> quertUsers(){
        return userService.queryUsers();
    }
}
