create table oauth2_basic_user
(
    id           bigint            null,
    name         varchar(255)      null,
    account      varchar(255)      null,
    password     varchar(255)      null,
    mobile       varchar(11)       null,
    email        varchar(50)       null,
    avatar_url   varchar(255)      null,
    deleted      tinyint default 0 not null comment '是否删除 0启用  1删除',
    source_from  varchar(255)      null,
    create_time  datetime          null,
    update_time  datetime          null,
    updater_name varchar(255)      null,
    creater_name varchar(255)      null,
    creater_id   bigint            null,
    updater_id   bigint            null
);

INSERT INTO oauth2_basic_user (id, name, account, password, mobile, email, avatar_url, deleted, source_from, create_time, update_time, updater_name, creater_name, creater_id, updater_id) VALUES (1, '云逸', 'admin', '$2a$10$K7nVcC.75YZSZU1Fq6G6buYujG.dolGYGPboh7eQbtkdFmB0EfN5K', '17683906991', '17683906991@163.com', '', 0, 'system', '2023-06-20 15:20:42', '2023-06-20 15:20:42', '', '', 1, 1);
