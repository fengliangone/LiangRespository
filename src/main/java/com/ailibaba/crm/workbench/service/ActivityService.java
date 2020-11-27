package com.ailibaba.crm.workbench.service;

import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.workbench.bean.Activity;
import com.ailibaba.crm.workbench.bean.ActivityQueryVo;
import com.ailibaba.crm.workbench.bean.ActivityRemark;

import java.util.List;

import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.workbench.mappers
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/17 17:12
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ActivityService {

    List<Map<String,String>> listActivity(ActivityQueryVo activityQueryVo);

    void saveActivity(Activity activity) throws CrmException;

    List<Map<String,String>> queryActivityById(String id);

    void updateActivity(Activity activity) throws CrmException;

    void deleteActivity(String id) throws CrmException;

    Activity queryActivityDetailById(String id);

    void updateActivityRemark(ActivityRemark activityRemark) throws CrmException;

    void deleteActivityRemark(String id) throws CrmException;

    void insertActivityRemark(ActivityRemark activityRemark) throws CrmException;
}
