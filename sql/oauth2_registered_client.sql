create table oauth2_registered_client
(
    id                            bigint                                 not null comment 'id'
        primary key,
    client_id                     varchar(100)                           not null,
    client_id_issued_at           datetime     default CURRENT_TIMESTAMP not null,
    client_secret                 varchar(200)                           null,
    client_secret_expires_at      datetime                               null,
    client_name                   varchar(200)                           not null,
    client_authentication_methods varchar(1000)                          not null,
    authorization_grant_types     varchar(1000)                          not null,
    redirect_uris                 varchar(1000)                          null,
    post_logout_redirect_uris     varchar(1000)                          null,
    scopes                        varchar(1000)                          not null,
    client_settings               varchar(2000)                          not null,
    token_settings                varchar(2000)                          not null,
    creater_id                    bigint       default 0                 not null comment '创建人id',
    creater_name                  varchar(255) default ''                not null comment '创建人名称',
    create_time                   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updater_id                    bigint       default 0                 not null comment '修改人id',
    updater_name                  varchar(255) default ''                not null comment '修改人名称',
    update_time                   datetime     default CURRENT_TIMESTAMP not null comment '修改时间',
    source_from                   varchar(32)  default ''                not null comment '来源系统syscode',
    deleted                       tinyint      default 0                 not null comment '0:启用,1:删除'
);

INSERT INTO oauth2_registered_client (id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, post_logout_redirect_uris, scopes, client_settings, token_settings, creater_id, creater_name, create_time, updater_id, updater_name, update_time, source_from, deleted) VALUES (1, 'messaging-client', '2025-08-24 17:41:06', '$2a$10$6snHkUlitAK7A41VwCG0LurtnavcCxojFxwNt0mdPmOiZ9iPfa/3K', '2025-09-11 15:10:06', '58ff6277-2709-422c-bf77-d2f0a92b3b38', 'client_secret_basic', 'refresh_token,client_credentials,authorization_code,urn:ietf:params:oauth:grant-type:sms_code', 'http://127.0.0.1:8001/oauth2/authorize,https://www.bilibili.com', '', 'openid,profile,message.read,message.write', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":true}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}', 0, '', '2023-09-11 07:08:04', 0, '', '2023-09-11 07:08:04', '', 0);
INSERT INTO oauth2_registered_client (id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, post_logout_redirect_uris, scopes, client_settings, token_settings, creater_id, creater_name, create_time, updater_id, updater_name, update_time, source_from, deleted) VALUES (2, 'device-message-client', '2025-08-24 17:41:06', null, '2023-09-11 15:10:13', '6867a3d3-08b1-4611-9e46-10ed1b821a3b', 'none', 'refresh_token,urn:ietf:params:oauth:grant-type:device_code', '', '', 'message.read,message.write', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":false,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}', 0, '', '2023-09-11 07:08:04', 0, '', '2023-09-11 07:08:04', '', 0);
INSERT INTO oauth2_registered_client (id, client_id, client_id_issued_at, client_secret, client_secret_expires_at, client_name, client_authentication_methods, authorization_grant_types, redirect_uris, post_logout_redirect_uris, scopes, client_settings, token_settings, creater_id, creater_name, create_time, updater_id, updater_name, update_time, source_from, deleted) VALUES (3, 'pkce-message-client', '2025-08-25 09:59:10', null, '2025-09-11 15:10:06', 'fe8c05f4-da8e-46c2-8171-244cdb4cdbdc', 'none', 'refresh_token,authorization_code', 'http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc', '', 'message.read,message.write', '{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":true,"settings.client.require-authorization-consent":false}', '{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],"settings.token.access-token-time-to-live":["java.time.Duration",300.000000000],"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat","value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000],"settings.token.device-code-time-to-live":["java.time.Duration",300.000000000]}', 0, '', '2023-09-11 07:08:04', 0, '', '2023-09-11 07:08:04', '', 0);
