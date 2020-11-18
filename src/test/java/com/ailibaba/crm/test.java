package com.ailibaba.crm;

import com.ailibaba.crm.base.constants.CrmExceptionEnum;
import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.utils.MD5Util;
import com.ailibaba.crm.utils.UUIDUtil;
import com.ailibaba.crm.workbench.bean.ActivityQueryVo;
import com.ailibaba.crm.workbench.mappers.ActivityMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/16 19:36
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class test {

    /**
     * 生成用户id
     */
    @Test
    public void test01(){
        String uid= UUIDUtil.getUUID();
        System.out.println(uid);
    }
    /**
     * MD5加密
     */
    @Test
    public void Test02(){
        String pwd=MD5Util.getMD5("admin");
        System.out.println(pwd);
    }


    /**
     * 自定义异常信息抛出测试
     */
    @Test
    public void Test03(){
        int a=0;
        if (a==0){
            try {
                throw new CrmException(CrmExceptionEnum.LOGIN_ACCOUNT_ERROR);
            } catch (CrmException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     *
     */
    @Test
    public void Test04(){
        BeanFactory beanFactory=
                new ClassPathXmlApplicationContext("spring/applicationContext.xml");
        SqlSessionFactory sqlSession= (SqlSessionFactory) beanFactory.getBean("sqlSessionFactory");
        ActivityMapper activityMapper=sqlSession.openSession().getMapper(ActivityMapper.class);
        ActivityQueryVo activityQueryVo=new ActivityQueryVo();
        List<Map<String,String>> maps=activityMapper.listActivity(activityQueryVo);
        for (Map<String, String> map : maps) {
            System.out.println(map);
        }
    }





















}
