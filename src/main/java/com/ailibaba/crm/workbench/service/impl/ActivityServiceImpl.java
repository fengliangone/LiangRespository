package com.ailibaba.crm.workbench.service.impl;

import com.ailibaba.crm.base.constants.CrmExceptionEnum;
import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.settings.bean.User;
import com.ailibaba.crm.settings.mapper.UserMapper;
import com.ailibaba.crm.utils.DateTimeUtil;
import com.ailibaba.crm.utils.UUIDUtil;
import com.ailibaba.crm.workbench.bean.Activity;
import com.ailibaba.crm.workbench.bean.ActivityQueryVo;
import com.ailibaba.crm.workbench.bean.ActivityRemark;
import com.ailibaba.crm.workbench.mappers.ActivityMapper;
import com.ailibaba.crm.workbench.mappers.ActivityRemarkMapper;
import com.ailibaba.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    @Autowired
    private UserMapper userMapper;



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

    @Override
    public void deleteActivity(String id) throws CrmException {

        //删除市场活动表
        int count=activityMapper.deleteByPrimaryKey(id);
//        根据市场活动备注表的外键删除备注信息
        Example example=new Example(ActivityRemark.class);
        example.createCriteria().andEqualTo("activityId",id);
        activityRemarkMapper.deleteByExample(example);

        if (count==0){
            throw new CrmException(CrmExceptionEnum.ACTIVITY__DELETE);
        }

    }

    @Override
    public Activity queryActivityDetailById(String id) {


        Activity activity=activityMapper.selectByPrimaryKey(id);
        //根据activity中的owner查询对用的用户
        User user = userMapper.selectByPrimaryKey(activity.getOwner());
        activity.setOwner(user.getName());
        /**
         * 当我们需要使用tk。mapper条件查询的时候
         */
        Example example=new Example(ActivityRemark.class);
        Example.Criteria criteria=example.createCriteria();
        criteria.andEqualTo("activityId",activity.getId());

        List<ActivityRemark> activityRemarkMappers=activityRemarkMapper.selectByExample(example);
        activity.setActivityRemarks(activityRemarkMappers);

        return activity;
    }

    @Override
    public void updateActivityRemark(ActivityRemark activityRemark) throws CrmException {
        activityRemark.setEditTime(DateTimeUtil.getSysTime());

        int count=activityRemarkMapper.updateByPrimaryKeySelective(activityRemark);

        if (count==0){
            throw new CrmException(CrmExceptionEnum.ACTIVITY__UPDATE_REMARK);
        }
    }

    @Override
    public void deleteActivityRemark(String id) throws CrmException {

        int count=activityRemarkMapper.deleteByPrimaryKey(id);
        if (count==0){
            throw  new CrmException(CrmExceptionEnum.ACTIVITY__DELETE_REMARK);
        }
    }

    @Override
    public void insertActivityRemark(ActivityRemark activityRemark) throws CrmException {
        activityRemark.setCreateTime(DateTimeUtil.getSysTime());
        activityRemark.setId(UUIDUtil.getUUID());
        activityRemark.setEditFlag("0");

        int count=activityRemarkMapper.insertSelective(activityRemark);
        if (count==0){
            throw new CrmException(CrmExceptionEnum.ACTIVITY__INSERT_REMARK);
        }
    }
}
