package com.ailibaba.crm.settings.bean;

import org.springframework.stereotype.Component;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.settings.bean
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/16 17:00
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Component
@Table(name = "tbl_user")
public class User {

    @Id
    private String id;
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
