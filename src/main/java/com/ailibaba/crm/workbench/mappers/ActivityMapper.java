package com.ailibaba.crm.workbench.mappers;

import com.ailibaba.crm.workbench.bean.Activity;
import com.ailibaba.crm.workbench.bean.ActivityQueryVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.workbench.mappers
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/17 17:13
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ActivityMapper extends Mapper<Activity> {

    List<Map<String, String>> listActivity(ActivityQueryVo activityQueryVo);

    List<Map<String, String>> queryActivityById(String id);
}
