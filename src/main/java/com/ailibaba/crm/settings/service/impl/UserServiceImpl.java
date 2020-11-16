package com.ailibaba.crm.settings.service.impl;

import com.ailibaba.crm.base.constants.CrmExceptionEnum;
import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.settings.bean.User;
import com.ailibaba.crm.settings.mapper.UserMapper;
import com.ailibaba.crm.settings.service.UserService;
import com.ailibaba.crm.utils.DateTimeUtil;
import com.ailibaba.crm.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.settings.service.impl
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/16 17:03
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> queryUsers() {
        return userMapper.selectAll();
    }

    @Override
    public User login(User user) throws CrmException {

        String ip=user.getAllowIps();
        //将用户输入的密码加密再进行比较
        user.setLoginPwd(MD5Util.getMD5(user.getLoginPwd()));
        user.setAllowIps(null);
        user=userMapper.selectOne(user);
        //登录账户不存在,账户或密码错误
        if (user==null){
            throw new CrmException(CrmExceptionEnum.LOGIN_ACCOUNT_ERROR);
        }else {
            //当前时间大于用户有效期，账户失效
            if (DateTimeUtil.getSysTime().compareTo(user.getExpireTime())>0){
                throw new CrmException(CrmExceptionEnum.LOGIN_ACCOUNT_EXPIRE);
            }
            //用户被禁用 0禁用
            if ("0".equals(user.getLockState())){
                throw new CrmException(CrmExceptionEnum.LOGIN_ACCOUNT_FORBID);
            }
            //ip地址是否在允许的ip地址内
            if (user.getAllowIps()!=null){
                if(!user.getAllowIps().contains(ip)){
                    throw new CrmException(CrmExceptionEnum.LOGIN_ACCOUNT_IP);
                }
            }
        }
        return user;
    }
}
