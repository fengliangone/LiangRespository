package com.ailibaba.crm.workbench.service;

import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.workbench.bean.*;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.workbench.service
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/22 20:28
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface ClueService {
    void saveClue(Clue clue) throws CrmException;

    Clue queryClueDetailById(String id);

    void updateClueRemark(ClueRemark clueRemark) throws CrmException;

    void saveClueRemark(ClueRemark clueRemark) throws CrmException;

    void deleteBind(ClueActivityRelation clueActivityRelation) throws CrmException;

    void deleteManyBind(String clueId, String activityIds) throws CrmException;

    List<Activity> queryActivityExculdeNow(String clueId, String activityName);

    void saveBind(String clueId,String activityName) throws CrmException;

    List<Activity> queryClueActivity(String clueId);

    List<Clue> listClue();

    void saveConvert(Transaction transaction,String id, String username, String isCreateTranaction) throws CrmException;

    List<Activity> queryActivityIncludeNow(String clueId, String activityName);
}
