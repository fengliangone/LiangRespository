package com.ailibaba.crm.settings.service.impl;

import com.ailibaba.crm.base.bean.DictionaryType;
import com.ailibaba.crm.base.bean.DictionaryValue;
import com.ailibaba.crm.settings.mapper.DictionaryTypeMapper;
import com.ailibaba.crm.settings.mapper.DictionaryValueMapper;
import com.ailibaba.crm.settings.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.settings.service.impl
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/22 19:16
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service("dictionaryService")
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryTypeMapper dictionaryTypeMapper;

    @Autowired
    private DictionaryValueMapper dictionaryValueMapper;


    @Override
    public List<DictionaryType> queryDictionary() {
        List<DictionaryType> dictionaryTypes=dictionaryTypeMapper.selectAll();
        for (DictionaryType dictionaryType : dictionaryTypes) {
            //取出每个字典类型的主键，查询该类型下的所有字典类型对应的values
            Example example = new Example(DictionaryValue.class);
            //按orderNo进行升序排序
            example.setOrderByClause("orderNo");
            example.createCriteria().andEqualTo("typeCode",dictionaryType.getCode());

            List<DictionaryValue> dictionaryValues=dictionaryValueMapper.selectByExample(example);
            dictionaryType.setDictionaryValues(dictionaryValues);
        }
        
        return dictionaryTypes;
    }
}
