package com.ailibaba.crm.base.cache;

import com.ailibaba.crm.base.bean.DictionaryType;
import com.ailibaba.crm.settings.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resources;
import javax.servlet.ServletContext;
import java.util.*;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.base.cache
 * @Description: 数据字典写入servletContexxt
 * @Author: 亮哥
 * @CreateDate: 2020/11/22 19:07
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Component
public class CrmCache {

    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private ServletContext servletContext;

    //在bean对象初始化时，创建方法
    @PostConstruct
    public void cache(){
        List<DictionaryType> dictionaryTypes=dictionaryService.queryDictionary();
//        将dictionaryTypes放入ServletContext
        servletContext.setAttribute("dictionaryTypes",dictionaryTypes);

//        通过io流读取Stage2Possibility.properties  包名.属性文件
        ResourceBundle resourceBundle=ResourceBundle.getBundle("mybatis.Stage2Possibility");
        Enumeration<String> keys = resourceBundle.getKeys();
        Map<String,String> map=new TreeMap<>();
        while (keys.hasMoreElements()){
            String key=keys.nextElement();
            String value=resourceBundle.getString(key);
            map.put(key,value);
        }
        servletContext.setAttribute("stage2PossibilityMap",map);

    }
}
