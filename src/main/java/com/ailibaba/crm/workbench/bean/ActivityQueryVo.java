package com.ailibaba.crm.workbench.bean;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.bean
 * @Description: 查询条件
 * @Author: Andy
 * @CreateDate: 2020/11/17 14:07
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class ActivityQueryVo {
    private String name;
    private String owner;
    private String startTime;
    private String endTime;


    @Override
    public String toString() {
        return "ActivityQueryVo{" +
                "name='" + name + '\'' +
                ", owner='" + owner + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}