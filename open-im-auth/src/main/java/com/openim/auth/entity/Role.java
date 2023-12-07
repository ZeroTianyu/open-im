package com.openim.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.openim.common.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author vains
 * @since 2023-07-04
 */
@Getter
@Setter
@TableName("role")
public class Role extends BaseEntity {

    /**
     * 角色名
     */
    private String roleName;

    /**
     * 排序
     */
    private Integer sort;

}
