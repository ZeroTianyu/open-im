package com.openim.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.openim.auth.entity.Oauth2BasicUser;
import com.openim.auth.entity.Oauth2ThirdAccount;

/**
 * <p>
 * 基础用户信息表 服务类
 * </p>
 *
 * @author vains
 * @since 2023-07-04
 */
public interface IOauth2BasicUserService extends IService<Oauth2BasicUser> {

    /**
     * 生成用户信息
     *
     * @param thirdAccount 三方用户信息
     * @return 用户id
     */
    Long saveByThirdAccount(Oauth2ThirdAccount thirdAccount);

}
