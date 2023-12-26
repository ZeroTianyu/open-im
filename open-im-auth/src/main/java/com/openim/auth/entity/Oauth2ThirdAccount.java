package com.openim.auth.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.openim.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 三方登录账户信息表
 * </p>
 *
 * @author vains
 * @since 2023-07-04
 */
@Getter
@Setter
@TableName("oauth2_third_Account")
public class Oauth2ThirdAccount extends BaseEntity {

    /**
     * 用户表主键
     */
    private Long userId;

    /**
     * 三方登录唯一id
     */
    private String uniqueId;

    /**
     * 三方登录用户名
     */
    private String thirdUsername;

    /**
     * 三方登录获取的认证信息
     */
    private String credentials;

    /**
     * 三方登录获取的认证信息的过期时间
     */
    private LocalDateTime credentialsExpiresAt;

    /**
     * 三方登录类型
     */
    private String type;

    /**
     * 博客地址
     */
    private String blog;

    /**
     * 地址
     */
    private String location;

    /**
     * 用户名、昵称
     */
    @TableField(exist = false)
    private String name;

    /**
     * 头像地址
     */
    @TableField(exist = false)
    private String avatar;
}
