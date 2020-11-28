package com.ailibaba.crm.workbench.bean;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.workbench.bean
 * @Description: java类作用描述
 * @Author: 亮哥
 * @CreateDate: 2020/11/28 19:33
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class TransactionEchartsResultVo {


    private Map<String, List<Map<String,String>>> dataMap;

    private List<String> dataList;

    @Override
    public String toString() {
        return "TransactionEchartsResultVo{" +
                "dataMap=" + dataMap +
                ", dataList=" + dataList +
                '}';
    }

    public Map<String, List<Map<String, String>>> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, List<Map<String, String>>> dataMap) {
        this.dataMap = dataMap;
    }

    public List<String> getDataList() {
        return dataList;
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
    }
}
