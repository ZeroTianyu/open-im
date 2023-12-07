package com.openim.user.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openim.common.entity.BaseEntity;
import lombok.Data;

@Data
@JsonSerialize
@TableName("user_detail")
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo extends BaseEntity {

    /**
     * 用户名、昵称
     */
    @TableField("name")
    private String name;
    /**
     * 头像地址
     */
    @TableField("avatar_url")
    private String avatarUrl;


}
