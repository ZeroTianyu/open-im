package com.openim.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.openim.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

/**
 * <p>
 * 角色菜单多对多关联表
 * </p>
 *
 * @author vains
 * @since 2023-07-04
 */
@Getter
@Setter
@TableName("role_authority")
public class RoleAuthority extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Integer roleId;

    /**
     * 权限菜单ID
     */
    private Integer authorityId;
}
