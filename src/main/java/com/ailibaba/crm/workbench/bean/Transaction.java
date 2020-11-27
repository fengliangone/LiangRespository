package com.ailibaba.crm.workbench.bean;

import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.bjpowernode.crm.workbench.bean
 * @Description: java类作用描述
 * @Author: Andy
 * @CreateDate: 2020/11/24 10:22
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Table(name = "tbl_tran")
@NameStyle(Style.normal)
public class Transaction {

    @Id
    private String id;
    private String owner;
    private String money;
    private String name;
    private String expectedDate;
    private String customerId;
    private String stage;
    private String type;
    private String source;
    private String activityId;
    private String contactsId;
    private String createBy;
    private String createTime;
    private String editBy;
    private String editTime;
    private String description;
    private String contactSummary;
    private String nextContactTime;

    private List<String> possibility;

    //交易备注
    private List<TransactionRemark> transactionRemarks;

    //交易历史信息
    private List<TransactionHistory> transactionHistories;


    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", owner='" + owner + '\'' +
                ", money='" + money + '\'' +
                ", name='" + name + '\'' +
                ", expectedDate='" + expectedDate + '\'' +
                ", customerId='" + customerId + '\'' +
                ", stage='" + stage + '\'' +
                ", type='" + type + '\'' +
                ", source='" + source + '\'' +
                ", activityId='" + activityId + '\'' +
                ", contactsId='" + contactsId + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createTime='" + createTime + '\'' +
                ", editBy='" + editBy + '\'' +
                ", editTime='" + editTime + '\'' +
                ", description='" + description + '\'' +
                ", contactSummary='" + contactSummary + '\'' +
                ", nextContactTime='" + nextContactTime + '\'' +
                ", possibility=" + possibility +
                ", transactionRemarks=" + transactionRemarks +
                ", transactionHistories=" + transactionHistories +
                '}';
    }

    public List<TransactionRemark> getTransactionRemarks() {
        return transactionRemarks;
    }

    public void setTransactionRemarks(List<TransactionRemark> transactionRemarks) {
        this.transactionRemarks = transactionRemarks;
    }

    public List<TransactionHistory> getTransactionHistories() {
        return transactionHistories;
    }

    public void setTransactionHistories(List<TransactionHistory> transactionHistories) {
        this.transactionHistories = transactionHistories;
    }



    public List<String> getPossibility() {
        return possibility;
    }

    public void setPossibility(List<String> possibility) {
        this.possibility = possibility;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(String expectedDate) {
        this.expectedDate = expectedDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getContactsId() {
        return contactsId;
    }

    public void setContactsId(String contactsId) {
        this.contactsId = contactsId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactSummary() {
        return contactSummary;
    }

    public void setContactSummary(String contactSummary) {
        this.contactSummary = contactSummary;
    }

    public String getNextContactTime() {
        return nextContactTime;
    }

    public void setNextContactTime(String nextContactTime) {
        this.nextContactTime = nextContactTime;
    }
}