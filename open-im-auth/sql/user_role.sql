create table user_role
(
    id           bigint            null,
    role_id      bigint            null,
    user_id      bigint            null,
    source_from  varchar(255)      null,
    create_time  datetime          null,
    update_time  datetime          null,
    updater_name varchar(255)      null,
    creater_name varchar(255)      null,
    creater_id   bigint            null,
    updater_id   bigint            null,
    deleted      tinyint default 0 not null comment '0:启用,1:删除'
);

INSERT INTO user_role (id, role_id, user_id, source_from, create_time, update_time, updater_name, creater_name, creater_id, updater_id, deleted) VALUES (1, 1, 1, '', '2022-03-25 23:52:03', '2022-03-25 23:52:03', '1', '1', 1, 1, 0);
