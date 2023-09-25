package com.openim.auth.model;


import com.alibaba.fastjson2.annotation.JSONField;

import java.beans.Transient;

/**
 * @Description 分页查询对象
 * @Version 1.0.0
 * @Author Created by yan.x on 2019-04-30 .
 * @Copyright © dgg group.All Rights Reserved.
 **/
public class OpenIMPageable<T> {
    /**
     * 当前页
     */
    private int currentPage = 1;

    /**
     * 每页数据
     */
    private int limit = 10;

    /**
     * 总页数
     */
    private int totalPage;

    /**
     * 总数据量
     */
    private long totalCount = 0;

    /**
     * 所有集合
     */
    private java.util.List<T> records = new java.util.ArrayList<>();

    /**
     * 设置总页数
     */
    public void calcTotalPage() {
        this.totalPage = (int) Math.ceil((double) totalCount / limit);
    }

    /**
     * 设置总页数
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 获取分页开始的位置(数据条数)
     */
    @Transient
    @JSONField(serialize = false)
    public int getBegin() {
        return this.getCurrentPage() < 1 ? 0 : (this.getCurrentPage() - 1) * this.getLimit();
    }

    /**
     * 设置分页开始的位置(数据条数)
     */
    public void setBegin(int currentPage) {
        setCurrentPage((int) Math.floor((double) currentPage / limit) + 1);
    }

    /**
     * 获取分页结束的位置(数据条数)
     */
    @Transient
    @JSONField(serialize = false)
    public int getEnd() {
        return this.getCurrentPage() * this.getLimit();
    }


    public static <T> OpenIMPageable<T> build() {
        return new OpenIMPageable<T>();
    }

    public static <T> OpenIMPageable<T> build(int currentPage, int limit) {
        return new OpenIMPageable<T>(currentPage, limit);
    }

    public static <T> OpenIMPageable<T> build(PageModel pageModel) {
        return new OpenIMPageable<T>(pageModel.getStart(), pageModel.getLimit());
    }

    public static <T> OpenIMPageable<T> build(int currentPage, int limit, long totalCount) {
        return new OpenIMPageable<T>(currentPage, limit, totalCount);
    }

    public static <T> OpenIMPageable<T> build(int currentPage, int limit, int upPage, int nextPage, int totalPage, java.util.List<T> records) {
        OpenIMPageable<T> pageList = new OpenIMPageable<>(currentPage, limit, records);
        pageList.setTotalPage(totalPage);
        return pageList;
    }

    public static <T> OpenIMPageable<T> build(int currentPage, int limit, long totalCount, java.util.List<T> records) {
        return new OpenIMPageable<T>(currentPage, limit, totalCount, records);
    }

    public static <T> OpenIMPageable<T> build(int currentPage, int limit, java.util.List<T> records) {
        return new OpenIMPageable<T>(currentPage, limit, records);
    }

    public static <T> OpenIMPageable<T> build(OpenIMPageable pageable, java.util.List<T> records) {
        return new OpenIMPageable<T>(pageable.getCurrentPage(), pageable.getLimit(), pageable.getTotalCount(), records);
    }

    public OpenIMPageable() {
    }

    /**
     * 分页
     *
     * @param limit       每页记录数
     * @param currentPage 当前页数
     */
    public OpenIMPageable(int currentPage, int limit) {
        this.setCurrentPage(currentPage);
        this.setLimit(limit);
    }

    /**
     * 分页
     *
     * @param limit       每页记录数
     * @param currentPage 当前页数
     * @param totalCount  总记录数
     */
    public OpenIMPageable(int currentPage, int limit, long totalCount) {
        this.setCurrentPage(currentPage);
        this.setLimit(limit);
        this.setTotalCount(totalCount);
        init();
    }

    /**
     * 分页
     *
     * @param records     列表数据
     * @param limit       每页记录数
     * @param currentPage 当前页数
     */
    public OpenIMPageable(int currentPage, int limit, java.util.List<T> records) {
        this.setCurrentPage(currentPage);
        this.setLimit(limit);
        this.setTotalCount(totalCount);
        this.setRecords(records);
    }

    /**
     * 分页
     *
     * @param records     列表数据
     * @param totalCount  总记录数
     * @param limit       每页记录数
     * @param currentPage 当前页数
     */
    public OpenIMPageable(int currentPage, int limit, long totalCount, java.util.List<T> records) {
        this.setCurrentPage(currentPage);
        this.setLimit(limit);
        this.setTotalCount(totalCount);
        this.setRecords(records);
        init();
    }

    /**
     * 初始化计算分页
     */
    private void init() {
        this.calcTotalPage();// 设置总页数
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public OpenIMPageable<T> setCurrentPage(int currentPage) {
        this.currentPage = currentPage < 1 ? 1 : currentPage;
        return this;
    }

    public int getLimit() {
        return limit;
    }

    public OpenIMPageable<T> setLimit(int limit) {
        this.limit = limit < 1 ? 10 : limit;
        return this;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public OpenIMPageable<T> setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.calcTotalPage();
        return this;
    }

    public java.util.List<T> getRecords() {
        return records;
    }

    public OpenIMPageable<T> setRecords(java.util.List<T> records) {
        this.records = records;
        return this;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public OpenIMPageable<T> setResult(long totalCount, java.util.List<T> records) {
        this.setTotalCount(totalCount);
        this.setRecords(records);
        return this;
    }

}
