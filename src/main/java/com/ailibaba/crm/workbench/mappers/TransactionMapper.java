package com.ailibaba.crm.workbench.mappers;


import com.ailibaba.crm.workbench.bean.Transaction;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface TransactionMapper extends Mapper<Transaction> {

    List<Map<String,String>> queryTransactionEcharts();
}
