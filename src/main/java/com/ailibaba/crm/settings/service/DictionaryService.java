package com.ailibaba.crm.settings.service;

import com.ailibaba.crm.base.bean.DictionaryType;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.settings.service
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/22 19:16
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface DictionaryService {

    List<DictionaryType> queryDictionary();
}
