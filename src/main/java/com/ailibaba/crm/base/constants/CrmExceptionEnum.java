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
    LOGIN_ACCOUNT_IP("001","不允许的ip地址"),

    ACTIVITY__SAVE("002","添加市场活动失败"),
    ACTIVITY__UPDATE("002","更新市场活动失败"),
    ACTIVITY__DELETE("002","删除市场活动失败"),
    ACTIVITY__UPDATE_REMARK("002","更新市场活动备注失败"),
    ACTIVITY__DELETE_REMARK("002","删除市场活动备注失败"),
    ACTIVITY__INSERT_REMARK("002","添加市场活动备注失败"),
    CLUE_SAVE("003","添加线索失败"),
    CLUE_REMARK_UPDATE("003","更新线索备注失败"),
    CLUE_REMARK_SAVE("003","添加线索备注失败"),
    CLUE_ACTIVITY_UNBIND("003","解除线索和市场活动关联失败"),
    CLUE_ACTIVITY_insert_Clue("003","线索和市场活动关联失败"),
    CLUE_CONVERT("003","线索转换失败"),
    TRANSACTION_SAVE("004","创建交易失败"),
    TRANSACTION_HISTORY_SAVE("004","创建交易历史失败"),
    CUSTOMER_SAVE("005","创建客户失败"),
    TRANSACTION_STAGE_UPDATE("004","更新交易失败");

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
