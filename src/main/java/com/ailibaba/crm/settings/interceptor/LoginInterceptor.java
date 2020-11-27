package com.ailibaba.crm.settings.interceptor;

import com.ailibaba.crm.base.constants.CrmConstants;
import com.ailibaba.crm.settings.bean.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.settings.interceptor
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/27 20:03
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class LoginInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取用户的请求地址
        String requestURL = request.getRequestURL().toString();
        if(requestURL.contains("login")){
            //登录的时候得放行
            return true;
        }
        //已经登录过的用户得放行
        User user = (User) request.getSession().getAttribute(CrmConstants.LOGIN_SUCESS);
        if(user != null){
            //用户登录了
            return true;
        }
        //重定向到登录页面
        response.sendRedirect("/crm/login.jsp");
        return false;
    }
}
