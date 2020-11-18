package com.ailibaba.crm.base.exception;

import com.ailibaba.crm.base.constants.CrmExceptionEnum;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.base.exception
 * @Description: 自定义异常
 * @Author: 亮哥
 * @CreateDate: 2020/11/16 19:58
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 * 1、给用户返回信息
 * 2、项目上线测试bug
 */
public class CrmException extends Exception{

    private String messages;
    private CrmExceptionEnum crmExceptionEnum;

    public CrmException(CrmExceptionEnum crmExceptionEnum) {
        super(crmExceptionEnum.getMess());//异常抛出
        this.crmExceptionEnum = crmExceptionEnum;
    }



    public CrmExceptionEnum getCrmExceptionEnum() {
        return crmExceptionEnum;
    }

    public void setCrmExceptionEnum(CrmExceptionEnum crmExceptionEnum) {
        this.crmExceptionEnum = crmExceptionEnum;
    }
}
