package com.ailibaba.crm.workbench.service;

import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.workbench.bean.Transaction;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.workbench.service
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/26 16:49
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface TransactionService {
    List<String> queryCustomerName(String customerName);

    void saveTransaction(Transaction transaction,String username,String company) throws CrmException;

    Transaction queryTransactionById(String id, Map<String, String> stage2PossibilityMap);

    List<Map<String,? extends Object>> stageList(String name, Integer index, String tranId, Map<String, String> map) throws CrmException;
}
