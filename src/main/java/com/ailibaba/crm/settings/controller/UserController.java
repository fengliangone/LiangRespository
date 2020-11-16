package com.ailibaba.crm.settings.controller;

import com.ailibaba.crm.base.constants.CrmConstants;
import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.settings.bean.User;
import com.ailibaba.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

    /**
     * 测试
     * @return
     */
    @RequestMapping("/quertUsers")
    @ResponseBody
    public List<User> quertUsers(){
        return userService.queryUsers();
    }

    /**
     * 登录
     * @param user 前端登录表单
     * @param model
     * @param request
     * @param session
     * @return 转发地址
     */
    @PostMapping("/settings/user/login")
    public String login(User user, Model model,
                        HttpServletRequest request,
                        HttpSession session){
        //请求的ip地址
        String ip=request.getRemoteAddr();
        user.setAllowIps(ip);
        try {
            user=userService.login(user);
        } catch (CrmException e) {
            //登录失败
           model.addAttribute("msg",e.getMessage());
            return "../../login";
        }
        //登录成功
        session.setAttribute(CrmConstants.LOGIN_SUCESS,user);
        return "index";
    }
}
