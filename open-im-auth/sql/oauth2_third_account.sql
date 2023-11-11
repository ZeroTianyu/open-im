create table oauth2_third_account
(
    id                     bigint                                 not null comment 'id'
        primary key,
    user_id                bigint                                 null comment '用户表主键',
    unique_id              varchar(255)                           null comment '三方登录唯一id',
    third_username         varchar(255)                           null comment '三方用户的账号',
    credentials            varchar(255)                           null comment '三方登录获取的认证信息(token)',
    credentials_expires_at datetime                               null comment '三方登录获取的认证信息过期时间',
    type                   varchar(255)                           null comment '三方登录类型',
    blog                   varchar(255)                           null comment '博客地址',
    location               varchar(255)                           null comment '地址',
    creater_id             bigint       default 0                 not null comment '创建人id',
    creater_name           varchar(255) default ''                not null comment '创建人名称',
    create_time            datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id             bigint       default 0                 not null comment '修改人id',
    updater_name           varchar(255) default ''                not null comment '修改人名称',
    update_time            datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    source_from            varchar(32)  default ''                not null comment '来源系统syscode',
    deleted                tinyint      default 0                 not null comment '0:启用,1:删除'
)
    comment '三方登录账户信息表' row_format = DYNAMIC;

