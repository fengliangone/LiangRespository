package com.ailibaba.crm.base.bean;

import com.ailibaba.crm.base.constants.CrmConstants;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.ailibaba.crm.base.bean
 * @Description: 分页对象
 * @Author: 亮哥
 * @CreateDate: 2020/11/17 19:56
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class PaginationVo<T>{

    private List<T> dataList;//每页数据
    private int page;//当前页数
    private int pageSize;//每页记录数
    private PageInfo<T> pageInfo;
    private int pages;//总页数
    private long count;//总记录数

    public PaginationVo() {
    }

    public PaginationVo(PageInfo<T> pageInfo) {
        page=pageInfo.getPageNum();
        this.pageSize=pageInfo.getPageSize();
        this.pageInfo = pageInfo;
        count=pageInfo.getTotal();
        pages= (int) (count%pageSize==0?count/pageSize:count/pageSize+1);
        dataList=pageInfo.getList();
    }

    @Override
    public String toString() {
        return "PaginationVo{" +
                "dataList=" + dataList +
                ", page='" + page + '\'' +
                ", pageSize=" + pageSize +
                ", pageInfo=" + pageInfo +
                ", pages=" + pages +
                ", count=" + count +
                '}';
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public PageInfo<T> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
