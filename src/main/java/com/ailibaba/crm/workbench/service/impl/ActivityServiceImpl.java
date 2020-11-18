package com.ailibaba.crm.workbench.service.impl;

import com.ailibaba.crm.base.constants.CrmExceptionEnum;
import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.utils.DateTimeUtil;
import com.ailibaba.crm.utils.UUIDUtil;
import com.ailibaba.crm.workbench.bean.Activity;
import com.ailibaba.crm.workbench.bean.ActivityQueryVo;
import com.ailibaba.crm.workbench.mappers.ActivityMapper;
import com.ailibaba.crm.workbench.mappers.ActivityRemarkMapper;
import com.ailibaba.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.workbench.service.impl
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/17 17:12
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;
    @Autowired
    private ActivityMapper activityMapper;



    @Override
    public List<Map<String, String>> listActivity(ActivityQueryVo activityQueryVo) {
        return activityMapper.listActivity(activityQueryVo);
    }

    @Override
    public void saveActivity(Activity activity) throws CrmException {
        //主键id，
        activity.setId(UUIDUtil.getUUID());
//        创建和修改时间
        activity.setCreateTime(DateTimeUtil.getSysTime());
        activity.setEditTime(DateTimeUtil.getSysTime());
//        非空不插入
        int count=activityMapper.insertSelective(activity);
//        插入失败
        if (count==0){
            throw new CrmException(CrmExceptionEnum.ACTIVITY__SAVE);
        }
    }

    @Override
    public List<Map<String,String>> queryActivityById(String id) {
        return activityMapper.queryActivityById(id);
    }

    @Override
    public void updateActivity(Activity activity) throws CrmException {
        // 修改时间
        activity.setEditTime(DateTimeUtil.getSysTime());

        int count=activityMapper.updateByPrimaryKeySelective(activity);
        //        更新失败
        if (count==0){
            throw new CrmException(CrmExceptionEnum.ACTIVITY__UPDATE);
        }
    }
}
