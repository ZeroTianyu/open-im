package com.openim.user.common.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.IdType.ASSIGN_ID;

/**
 * @Description: openim开放平台基础实体类
 * @Author: guoty
 * @Date: 2023/06/10
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(type = ASSIGN_ID)
    private Long id;

    /**
     * 创建人id
     */
    @TableField("creater_id")
    protected Long createrId;

    /**
     * 创建人名称
     */
    @TableField("creater_name")
    protected String createrName;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    protected LocalDateTime createTime;

    /**
     * 修改人id
     */
    @TableField("updater_id")
    protected Long updaterId;

    /**
     * 修改人名称
     */
    @TableField("updater_name")
    protected String updaterName;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    protected LocalDateTime updateTime;
    /**
     * 来源系统syscode
     */
    @TableField("source_from")
    protected String sourceFrom;


    /**
     * 设置创建人ID,创建人姓名,创建时间(当前)
     *
     * @param userId
     * @param userName
     */
    public void setCreateUser(Long userId, String userName) {
        this.createrId = userId;
        this.createrName = userName;
        this.createTime = LocalDateTime.now();
    }


    /**
     * 设置修改人ID,修改人姓名,修改时间(当前)
     *
     * @param userId
     * @param userName
     */
    public void setUpdaterUser(Long userId, String userName) {
        this.updaterId = userId;
        this.updaterName = userName;
        this.updateTime = LocalDateTime.now();
    }
}