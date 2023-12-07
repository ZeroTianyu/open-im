package com.openim.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.openim.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author vains
 * @since 2023-07-04
 */
@Getter
@Setter
@TableName("user_role")
public class UserRole extends BaseEntity {

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 用户ID
     */
    private Long userId;
}
