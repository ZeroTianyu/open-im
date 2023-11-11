package com.openim.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author vains
 * @since 2023-07-04
 */
@Getter
@Setter
@TableName("authority")
public class Authority extends BaseEntity {


    /**
     * 菜单名称
     */
    private String name;

    /**
     * 父菜单ID
     */
    private Long menuPid;

    /**
     * 跳转URL
     */
    private String url;

    /**
     * 所需权限
     */
    private String authority;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 0:菜单,1:接口
     */
    private Integer type;
}
