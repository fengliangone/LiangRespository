package com.ailibaba.crm.workbench.controller;

import com.ailibaba.crm.workbench.bean.TransactionEchartsResultVo;
import com.ailibaba.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.workbench.controller
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/28 19:21
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ChartController {


    @Autowired
    private TransactionService transactionService;
    @RequestMapping("/workbench/chart/transaction/queryTransactionEcharts")
    @ResponseBody
    public TransactionEchartsResultVo queryTransactionEcharts(){
        return transactionService.queryTransactionEcharts();
    }
}
