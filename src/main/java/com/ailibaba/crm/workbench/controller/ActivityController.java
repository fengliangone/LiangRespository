package com.ailibaba.crm.workbench.controller;

import com.ailibaba.crm.base.bean.PaginationVo;
import com.ailibaba.crm.base.bean.ResultVo;
import com.ailibaba.crm.base.constants.CrmConstants;
import com.ailibaba.crm.base.exception.CrmException;
import com.ailibaba.crm.settings.bean.User;
import com.ailibaba.crm.settings.service.UserService;
import com.ailibaba.crm.workbench.bean.Activity;
import com.ailibaba.crm.workbench.bean.ActivityQueryVo;
import com.ailibaba.crm.workbench.bean.ActivityRemark;
import com.ailibaba.crm.workbench.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
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
 * @CreateDate: 2020/11/17 17:12
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;


    /**
     * 模拟查询市场数据
     * @param page 当前页码
     * @param pageSize 每页记录数
     * @param activityQueryVo 模糊查询条件
     * @return
     */
    @RequestMapping("/workbench/activity/listActivity")
    @ResponseBody
    public PaginationVo ListActivity(
            @RequestParam(defaultValue = "1",required = false) int page,
            @RequestParam(defaultValue = "2",required = false) int pageSize,
            ActivityQueryVo activityQueryVo){
//        开启分页
        PageHelper.startPage(page, pageSize);

        List<Map<String,String>> maps=activityService.listActivity(activityQueryVo);

        PageInfo<Map<String,String>> pageInfo=new PageInfo<>(maps);
//        封装分页数据
        PaginationVo paginationVo=new PaginationVo(pageInfo);

        return paginationVo;
    }

    /**
     * 新建市场活动时，查询所有人
     * @return
     */
    @RequestMapping("/workbench/activity/queryUsers")
    @ResponseBody
    public List<User> queryUsers(){
       return userService.queryUsers();
    }

    /**
     *   保存市场活动用户
     * @param activity 新建市场活动表单
     * @param session
     * @return 客户端返回数据
     */
    @RequestMapping("/workbench/activity/saveActivity")
    @ResponseBody
    public ResultVo saveActivity(Activity activity, HttpSession session){
//        获取登录用户
        User user= (User) session.getAttribute("User");
        activity.setCreateBy(user.getName());
        activity.setEditBy(user.getName());
        ResultVo resultVo=new ResultVo();

        try{
            activityService.saveActivity(activity);
            resultVo.setSucess(true);
            resultVo.setMess("添加成功");
        } catch (CrmException e){
            resultVo.setSucess(false);
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    @RequestMapping("/workbench/activity/queryActivityById")
    @ResponseBody
    public List<Map<String,String>>  queryActivityById(String id){

        List<Map<String,String>> list =activityService.queryActivityById(id);
            return list;
    }

    /**
     * 更新市场信息
     * @param activity
     * @param session
     * @return
     */
    @RequestMapping("/workbench/activity/updateActivity")
    @ResponseBody
    public ResultVo updateActivity(Activity activity, HttpSession session){

        //修改人
        User user= (User) session.getAttribute("User");
        activity.setEditBy(user.getName());

        ResultVo resultVo=new ResultVo();

        try{
            activityService.updateActivity(activity);
            resultVo.setSucess(true);
            resultVo.setMess("更新成功");
        } catch (CrmException e){
            resultVo.setSucess(false);
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    /**
     * 删除市场活动
     * @param id
     * @return
     */
    @RequestMapping("/workbench/activity/deleteActivity")
    @ResponseBody
    public ResultVo deleteActivity(String id){
        ResultVo resultVo=new ResultVo();

        try{
            activityService.deleteActivity(id);
            resultVo.setSucess(true);
            resultVo.setMess("删除成功");
        } catch (CrmException e){
            resultVo.setSucess(false);
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    /**
     * 市场活动和备注查询
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/workbench/activity/queryActivityDetailById")
    public String queryActivityDetailById(String id, Model model){
        Activity activity=activityService.queryActivityDetailById(id);

        model.addAttribute("activity",activity);

        return "forward:/toView/activity/detail";
    }

    /**
     * 异步修改备注内容
     * @param activityRemark
     * @return
     */
    @RequestMapping("/workbench/activity/updateActivityRemark")
    @ResponseBody
    public ResultVo updateActivityRemark(ActivityRemark activityRemark){

        ResultVo resultVo = new ResultVo();
        try {
            activityService.updateActivityRemark(activityRemark);
            resultVo.setSucess(true);
            resultVo.setMess("修改备注成功");
        }catch (CrmException e){
            resultVo.setSucess(false);
            //将异常信息添加到resultVo中
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    /**
     * 删除备注
     * @param id
     * @return
     */
    @RequestMapping("/workbench/activity/deleteActivityRemark")
    @ResponseBody
    public ResultVo deleteActivityRemark(String id){

        ResultVo resultVo = new ResultVo();
        try {
            activityService.deleteActivityRemark(id);
            resultVo.setSucess(true);
            resultVo.setMess("删除备注成功");
        }catch (CrmException e){
            resultVo.setSucess(false);
            //将异常信息添加到resultVo中
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    /**
     * 添加市场备注
     * @param activityRemark
     * @return
     */
    @RequestMapping("/workbench/activity/saveActivityRemark")
    @ResponseBody
    public ResultVo saveActivityRemark(ActivityRemark activityRemark,HttpSession session){
        ResultVo resultVo = new ResultVo();
        User user= (User) session.getAttribute("User");
        activityRemark.setCreateBy(user.getName());
        try {
            activityService.insertActivityRemark(activityRemark);
            resultVo.setSucess(true);
            resultVo.setMess("添加备注成功");
        }catch (CrmException e){
            resultVo.setSucess(false);
            //将异常信息添加到resultVo中
            resultVo.setMess(e.getMessage());
        }
        return resultVo;
    }

    //在市场详细详细页面，根据主键删除市场活动
    @RequestMapping("/workbench/activity/deleteActivityByDetail")
    public String deleteActivityByDetail(String id){
        try{
            //删除成功
            activityService.deleteActivity(id);
        }catch (CrmException e){
            e.printStackTrace();
        }
        return "redirect:/toView/activity/index";
    }

}
