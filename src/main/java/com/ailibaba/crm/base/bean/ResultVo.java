package com.ailibaba.crm.base.bean;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.base.bean
 * @Description: 客户端返回消息
 * @Author: 亮哥
 * @CreateDate: 2020/11/18 9:56
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class ResultVo {

//    是否成功
    private boolean isOk;
//    消息
    private String mess;

    @Override
    public String toString() {
        return "ResultVo{" +
                "isOk=" + isOk +
                ", mess='" + mess + '\'' +
                '}';
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }

    public String getMess() {
        return mess;
    }

    public void setMess(String mess) {
        this.mess = mess;
    }
}
