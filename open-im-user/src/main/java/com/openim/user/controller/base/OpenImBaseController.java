package com.openim.user.controller.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.openim.common.model.OpenIMPageable;
import com.openim.common.model.OpenIMResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class OpenImBaseController extends AbstractCrispsController {


    /**
     * 正确返回
     *
     * @param entity
     * @return
     */
    protected <T> OpenIMResponse<T> getSuccess(T entity) {
        return success(entity);
    }

    /**
     * 返回分页数据
     *
     * @param page 分页对象
     * @param list list
     * @param <E>  泛型
     * @return page response
     */
    protected <E> OpenIMResponse<OpenIMPageable<E>> page(IPage page, List<E> list) {
        return success(OpenIMPageable.build((int) page.getCurrent(), (int) page.getSize(), page.getTotal(), list));
    }

    /**
     * 构造分页
     *
     * @param page 分页
     * @param <E>  泛型
     * @return page response
     */
    protected <E> OpenIMResponse<OpenIMPageable<E>> page(Page<E> page) {
        return page(page, page.getRecords());
    }


}
