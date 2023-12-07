package com.openim.common.model.jwt;

import lombok.Data;

import java.util.List;


/**
 * jwt中 数据对象 Payloads
 */
@Data
public class JwtPayload {
    /**
     * 用户
     */
    private String sub;
    /**
     * 接收者
     */
    private String aud;
    /**
     * 生效时间
     */
    private Long nbf;
    /**
     * 权限
     */
    private List<String> scope;
    /**
     * 签发者
     */

    private String iss;
    /**
     * 到期时间
     */
    private Long exp;
    /**
     * 签发时间
     */
    private Long iat;
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nikeName;


    /**
     * 权限列表
     */
    public List<String> authorities;
}
