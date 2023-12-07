package com.openim.user.model.response;

import com.openim.common.entity.BaseEntity;
import lombok.Data;

@Data
public class UserInfoResponse extends BaseEntity {

    /**
     * 用户名、昵称
     */
    private String name;
    /**
     * 头像地址
     */
    private String avatarUrl;

}
