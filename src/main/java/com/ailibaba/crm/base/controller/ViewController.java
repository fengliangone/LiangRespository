package com.ailibaba.crm.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.base.controller
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/16 21:16
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class ViewController {


    /**
     * 跳转所有视图  required:默认是true,每次必须给该变量传值，false可以传值，也可以不传值
     * @param modalView
     * @param view
     * @return
     */
    @RequestMapping({"/toView/{modalView}/{view}","/toView/{view}"})
    public String toView(@PathVariable(value = "modalView",required = false) String modalView,
                         @PathVariable("view") String view){

        if (modalView==null){
            return view;
        }else {
            return modalView+ File.separator+view;
        }

    }
}
