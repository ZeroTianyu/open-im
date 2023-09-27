create table role_authority
(
    id           bigint            null,
    role_id      int               null,
    authority_id int               null,
    source_from  varchar(255)      null,
    create_time  datetime          null,
    update_time  datetime          null,
    updater_name varchar(255)      null,
    creater_name varchar(255)      null,
    creater_id   bigint            null,
    updater_id   bigint            null,
    deleted      tinyint default 0 not null comment '0:启用,1:删除'
);

INSERT INTO role_authority (id, role_id, authority_id, source_from, create_time, update_time, updater_name, creater_name, creater_id, updater_id, deleted) VALUES (1, 1, 1, '', '2022-03-25 23:52:03', '2022-03-25 23:52:03', '1', '1', 1, 1, 0);
INSERT INTO role_authority (id, role_id, authority_id, source_from, create_time, update_time, updater_name, creater_name, creater_id, updater_id, deleted) VALUES (2, 1, 2, '', '2022-03-25 23:52:03', '2022-03-25 23:52:03', '1', '1', 1, 1, 0);
INSERT INTO role_authority (id, role_id, authority_id, source_from, create_time, update_time, updater_name, creater_name, creater_id, updater_id, deleted) VALUES (3, 1, 3, '', '2022-03-25 23:52:03', '2022-03-25 23:52:03', '1', '1', 1, 1, 0);
