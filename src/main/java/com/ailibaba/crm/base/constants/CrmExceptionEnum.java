package com.ailibaba.crm.base.constants;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.base.constants
 * @Description: 异常枚举类型
 * @Author: 亮哥
 * @CreateDate: 2020/11/16 20:00
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public enum CrmExceptionEnum {

    LOGIN_ACCOUNT_ERROR("001","用户名或密码异常"),
    LOGIN_ACCOUNT_EXPIRE("001","账户失效"),
    LOGIN_ACCOUNT_FORBID("001","账户已被禁用"),
    LOGIN_ACCOUNT_IP("001","不允许的ip地址");
    //    根据状态码判断异常
    private String code;//业务状态码  001:用户登录  002：交易模块

    private String mess;

    CrmExceptionEnum() {
    }

    CrmExceptionEnum(String code, String mess) {
        this.code = code;
        this.mess = mess;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }}
