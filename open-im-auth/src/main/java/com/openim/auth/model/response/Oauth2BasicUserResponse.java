package com.openim.auth.model.response;

import com.openim.common.entity.BaseEntity;
import lombok.Data;

@Data
public class Oauth2BasicUserResponse extends BaseEntity {


    /**
     * 用户名、昵称
     */
    private String name;

    /**
     * 账号
     */
    private String account;

    /**
     * 头像地址
     */
    private String avatarUrl;
}
