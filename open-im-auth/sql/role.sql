create table role
(
    id           bigint            null,
    role_name    varchar(16)       null,
    deleted      tinyint default 0 null,
    sort         int               null,
    source_from  varchar(255)      null,
    create_time  datetime          null,
    update_time  datetime          null,
    updater_name varchar(255)      null,
    creater_name varchar(255)      null,
    creater_id   bigint            null,
    updater_id   bigint            null
);

INSERT INTO role (id, role_name, deleted, sort, source_from, create_time, update_time, updater_name, creater_name, creater_id, updater_id) VALUES (1, '管理员', 0, 0, '', '2022-03-25 23:52:03', '2022-03-25 23:52:03', '1', '1', 1, 1);
