create table authority
(
    id           bigint            null,
    name         varchar(16)       null,
    menu_pid     int               null,
    url          varchar(64)       null,
    authority    varchar(255)      null,
    sort         tinyint           null,
    type         tinyint           null,
    deleted      tinyint default 0 null comment '是否删除 0启用  1删除',
    source_from  varchar(255)      null,
    create_time  datetime          null,
    update_time  datetime          null,
    updater_name varchar(255)      null,
    creater_name varchar(255)      null,
    creater_id   bigint            null,
    updater_id   bigint            null
);

INSERT INTO authority (id, name, menu_pid, url, authority, sort, type, deleted, source_from, create_time, update_time, updater_name, creater_name, creater_id, updater_id) VALUES (1, '系统管理', 0, '/system', 'system', 0, 0, 0, '', '2022-03-25 23:52:03', '2022-03-25 23:52:03', '1', '1', 1, 1);
INSERT INTO authority (id, name, menu_pid, url, authority, sort, type, deleted, source_from, create_time, update_time, updater_name, creater_name, creater_id, updater_id) VALUES (2, 'app', 0, '/**', 'app', 1, 1, 0, '', '2022-03-25 23:52:03', '2022-03-25 23:52:03', '1', '1', 1, 1);
INSERT INTO authority (id, name, menu_pid, url, authority, sort, type, deleted, source_from, create_time, update_time, updater_name, creater_name, creater_id, updater_id) VALUES (3, 'web', 0, '/**', 'web', 2, 1, 0, '', '2022-03-25 23:52:03', '2022-03-25 23:52:03', '1', '1', 1, 1);
