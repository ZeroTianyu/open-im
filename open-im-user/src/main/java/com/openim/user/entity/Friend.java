package com.openim.user.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openim.common.entity.BaseEntity;
import lombok.Data;

@Data
@JsonSerialize
@TableName("friend")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Friend extends BaseEntity {
    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 好友id
     */
    @TableField("friend_id")
    private Long friendId;

}
