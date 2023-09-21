package com.openim.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.openim.auth.entity.Authority;

import java.util.List;

/**
 * <p>
 * 系统菜单表 服务类
 * </p>
 *
 * @author vains
 * @since 2023-07-04
 */
public interface IAuthorityService extends IService<Authority> {

    /**
     * 根据用户id获取权限列表
     *
     * @param userId 用户id
     * @return 权限列表
     */
    List<Authority> getByUserId(Long userId);

}
