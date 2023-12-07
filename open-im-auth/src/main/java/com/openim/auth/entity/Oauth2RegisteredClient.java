package com.openim.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.openim.common.entity.BaseEntity;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@JsonSerialize
@TableName("oauth2_registered_client")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Oauth2RegisteredClient extends BaseEntity {
    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端签发时间
     */
    private LocalDateTime clientIdIssuedAt;

    /**
     * 客户端密钥
     */
    private String clientSecret;

    /**
     * 客户端密钥过期时间
     */
    private LocalDateTime clientSecretExpiresAt;
    /**
     * 客户端名称
     */
    private String clientName;
    /**
     * 客户端认证方式，基于请求头的认证
     */
    private String clientAuthenticationMethods;
    /**
     * 配置资源服务器使用该客户端获取授权时支持的方式
     */
    private String authorizationGrantTypes;
    /**
     * 重定向列表
     */
    private String redirectUris;
    /**
     * 登出重定向链接列表
     */
    private String postLogoutRedirectUris;

    /**
     * 权限
     */
    private String scopes;

    /**
     * 客户端设置
     */
    private String clientSettings;

    /**
     * token设置
     */
    private String tokenSettings;
}
