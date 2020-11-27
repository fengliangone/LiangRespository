package com.ailibaba.crm.workbench.controller;

import com.ailibaba.crm.base.constants.CrmConstants;
import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.settings.bean.User;
import com.ailibaba.crm.workbench.bean.Transaction;
import com.ailibaba.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.workbench.controller
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/26 16:21
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    //只能返回字符串｛1，2，3，4｝
    /**
     * 自动补全客户姓名
     * @param customerName
     * @return
     */
    @RequestMapping("/workbench/transaction/queryCustomerName")
    @ResponseBody
    public List<String> queryCustomerName(@RequestParam String customerName){
        return transactionService.queryCustomerName(customerName);
    }


    /**
     * 选择阶段，返回可能性
     * @param stage
     * @param session
     * @return
     */
    @RequestMapping("/workbench/transaction/queryPossibilityByStage")
    @ResponseBody
    public String queryPossibilityByStage(String stage, HttpSession session){
        Map<String,String> map=
                (Map<String, String>) session.getServletContext().getAttribute("stage2PossibilityMap");
        return map.get(stage);
    }

    /**
     * 创建交易
     * @param transaction
     * @param session
     * @return
     */
    @RequestMapping("/workbench/transaction/saveTransaction")
    public String saveTransaction(Transaction transaction,String company,HttpSession session){
        try {
            User user= (User) session.getAttribute(CrmConstants.LOGIN_SUCESS);
            transactionService.saveTransaction(transaction,user.getName(),company);
        } catch (CrmException e) {
            e.printStackTrace();
        }

        return "/transaction/index";
    }

    /**
     * 跳转到交易详情页
     * @param id
     * @param session
     * @param model
     * @return
     */
    @RequestMapping("/workbench/transaction/queryTransactionById")
    public String queryTransactionById(String id, HttpSession session, Model model){
        Map<String,String> map =
                (Map<String, String>) session.getServletContext().getAttribute("stage2PossibilityMap");
        Transaction transaction=transactionService.queryTransactionById(id,map);

        model.addAttribute("transaction",transaction);
        return "/transaction/detail";
    }

    @RequestMapping("/workbench/transaction/stageList")
    @ResponseBody
    public List<Map<String, ? extends Object>> stageList(Integer index,String tranId,HttpSession session){
        Map<String,String> map =
                (Map<String, String>) session.getServletContext().getAttribute("stage2PossibilityMap");
        User user = (User) session.getAttribute(CrmConstants.LOGIN_SUCESS);
        List<Map<String, ? extends Object>> stageList = null;
        try {
            stageList = transactionService.stageList(user.getName(),index,tranId,map);
        } catch (CrmException e) {
            e.printStackTrace();
        }
        return stageList;
    }
}

