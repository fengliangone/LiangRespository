package com.ailibaba.crm.workbench.controller;

import com.ailibaba.crm.base.bean.PaginationVo;
import com.ailibaba.crm.base.bean.ResultVo;
import com.ailibaba.crm.base.cache.CrmCache;
import com.ailibaba.crm.base.constants.CrmConstants;
import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.settings.bean.User;
import com.ailibaba.crm.workbench.bean.*;
import com.ailibaba.crm.workbench.service.ClueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
 * @CreateDate: 2020/11/22 20:27
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ClueController {

    @Autowired
    private ClueService clueService;


    /**
     * 模拟查询线索
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @param
     * @return
     */
    @RequestMapping("/workbench/clue/listClue")
    @ResponseBody
    public PaginationVo listClue(
            @RequestParam(defaultValue = "1",required = false) int page,
            @RequestParam(defaultValue = "2",required = false) int pageSize
            ){
//        开启分页
        PageHelper.startPage(page, pageSize);

        List<Clue> maps=clueService.listClue();

        PageInfo<Clue> pageInfo=new PageInfo<>(maps);
//        封装分页数据
        PaginationVo paginationVo=new PaginationVo(pageInfo);

        return paginationVo;
    }


    @RequestMapping("/workbench/clue/saveClue")
    @ResponseBody
    public ResultVo saveClue(Clue clue, HttpSession session) {

        ResultVo resultVo = new ResultVo();
        User user = (User) session.getAttribute(CrmConstants.LOGIN_SUCESS);
        clue.setCreateBy(user.getName());
        try {
            clueService.saveClue(clue);
            resultVo.setSucess(true);
            resultVo.setMess("添加线索成功");
        } catch (CrmException e) {
            resultVo.setSucess(false);
            resultVo.setMess(e.getMessage());
        }

        return resultVo;
    }

    @RequestMapping("/workbench/clue/queryClueDetailById")
    public String queryClueDetailById(String id, Model model) {

        Clue clue = clueService.queryClueDetailById(id);
        model.addAttribute("clue", clue);
        return "/clue/detail";
    }

    /**
     * 更新线索备注
     *
     * @param clueRemark 线索备注
     * @param session
     * @return
     */
    @RequestMapping("/workbench/clue/updateClueRemark")
    @ResponseBody
    public ResultVo updateClueRemark(ClueRemark clueRemark, HttpSession session) {
        ResultVo resultVo = new ResultVo();
        User user = (User) session.getAttribute(CrmConstants.LOGIN_SUCESS);
        clueRemark.setEditBy(user.getName());
        try {
            clueService.updateClueRemark(clueRemark);
            resultVo.setSucess(true);
            resultVo.setMess("更新线索备注成功");
        } catch (CrmException e) {
            resultVo.setSucess(false);
            resultVo.setMess(e.getMessage());
        }

        return resultVo;
    }

    /**
     * 添加线索备注
     *
     * @param clueRemark
     * @param session
     * @return
     */
    @RequestMapping("/workbench/clue/saveClueRemark")
    @ResponseBody
    public ResultVo saveClueRemark(ClueRemark clueRemark, HttpSession session) {
        ResultVo resultVo = new ResultVo();
        User user = (User) session.getAttribute(CrmConstants.LOGIN_SUCESS);
        clueRemark.setCreateBy(user.getName());
        try {
            clueService.saveClueRemark(clueRemark);
            resultVo.setSucess(true);
            resultVo.setMess("添加线索备注成功");
        } catch (CrmException e) {
            resultVo.setSucess(false);
            resultVo.setMess(e.getMessage());
        }

        return resultVo;
    }

    /**
     * 解除市场和线索关联
     *
     * @param clueActivityRelation
     * @return
     */
    @RequestMapping("/workbench/clue/deleteBind")
    @ResponseBody
    public ResultVo deleteBind(ClueActivityRelation clueActivityRelation) {
        ResultVo resultVo = new ResultVo();
        try {
            clueService.deleteBind(clueActivityRelation);
            resultVo.setSucess(true);
            resultVo.setMess("解除关联成功");
        } catch (CrmException e) {
            resultVo.setSucess(false);
            resultVo.setMess(e.getMessage());
        }

        return resultVo;

    }

    /**
     * 批量删除
     * @param clueId
     * @param activityIds
     * @return
     */
    @RequestMapping("/workbench/clue/deleteManyBind")
    @ResponseBody
    public ResultVo deleteManyBind(String clueId, String activityIds) {
        ResultVo resultVo = new ResultVo();
        try {
            clueService.deleteManyBind(clueId, activityIds);
            resultVo.setSucess(true);
            resultVo.setMess("解除关联成功");
        } catch (CrmException e) {
            resultVo.setSucess(false);
            resultVo.setMess(e.getMessage());
        }
        return resultVo;

    }

    /**
     * 查询关联市场活动
     * @param clueId
     * @param activityName
     * @return
     */
    @RequestMapping("/workbench/clue/queryActivityExculdeNow")
    @ResponseBody
    public List<Activity> queryActivityExculdeNow(String clueId, String activityName) {

        List<Activity> activities = clueService.queryActivityExculdeNow(clueId, activityName);
        return activities;
    }

    /**
     * 关联市场活动和线索
     * @param
     * @return
     */
    @RequestMapping("/workbench/clue/saveBind")
    @ResponseBody
    public ResultVo saveBind(String clueId, String activityIds){
        ResultVo resultVo = new ResultVo();
        try {
            clueService.saveBind(clueId,activityIds);
            resultVo.setSucess(true);
            resultVo.setMess("关联成功");
        } catch (CrmException e) {
            resultVo.setSucess(false);
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    //线索和市场活动关联成功后，再异步查询关联后的所有市场活动
    @RequestMapping("/workbench/clue/queryClueActivity")
    @ResponseBody
    public List<Activity> queryClueActivity(String clueId){
        List<Activity> activities = clueService.queryClueActivity(clueId);
        return activities;
    }

    /**
     * 跳转线索转换页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/workbench/clue/toConvertView")
    public String toConvertView(String id,Model model){
        Clue clue = clueService.queryClueDetailById(id);
        model.addAttribute("clue", clue);
        return "/clue/convert";

    }

    //线索转换发生交易查询当前线索下的所有市场活动
    @RequestMapping("/workbench/clue/queryActivityIncludeNow")
    @ResponseBody
    public List<Activity> queryActivityIncludeNow(String clueId,String activityName){
        List<Activity> activities = clueService.queryActivityIncludeNow(clueId,activityName);
        return activities;
    }

    /**
     * 线索转换
     * @return
     */
    @RequestMapping("/workbench/clue/convert")
    public String convert(Transaction transaction, String clueId, HttpSession session, String isCreateTransaction){
        User user= (User) session.getAttribute(CrmConstants.LOGIN_SUCESS);
        try {
            clueService.saveConvert(transaction,clueId,user.getName(),isCreateTransaction);
        } catch (CrmException e) {
            e.printStackTrace();
        }

        return "redirect:/toView/clue/index";
    }
}