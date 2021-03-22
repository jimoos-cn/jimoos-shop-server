--liquibase formatted sql

--changeset kison:1

CREATE TABLE `t_user`
(
    `id`          bigint(20)          NOT NULL AUTO_INCREMENT COMMENT 'id',
    `phone`       varchar(15)         NOT NULL DEFAULT '' COMMENT '手机号',
    `nickname`    varchar(30)                  DEFAULT '' COMMENT '昵称',
    `avatar`      varchar(255)                 DEFAULT '' COMMENT '头像',
    `password`    varchar(128)                 DEFAULT '' COMMENT '密码',
    `salt`        varchar(30)                  DEFAULT NULL COMMENT '盐',
    `gender`      tinyint(2)          NOT NULL DEFAULT '0' COMMENT '性别',
    `role`        tinyint(2) unsigned NOT NULL DEFAULT '0' COMMENT '角色 ',
    `invite_code` varchar(8)          NOT NULL COMMENT '邀请码',
    `province`    varchar(255)                 DEFAULT NULL COMMENT '省',
    `city`        varchar(255)                 DEFAULT NULL COMMENT '市',
    `area`        varchar(255)                 DEFAULT NULL COMMENT '县',
    `birthday`    bigint(20)                   DEFAULT '978278400000' COMMENT '生日',
    `create_at`   bigint(20)                   DEFAULT '0' COMMENT '创建时间',
    `update_at`   bigint(20)                   DEFAULT '0' COMMENT '更新时间',
    `ban`         tinyint(1)          NOT NULL COMMENT '0 未禁止 1 已禁止',
    `deleted`     tinyint(1)          NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;


create table t_user_address
(
    id         bigint unsigned auto_increment comment 'id '
        primary key,
    name       varchar(50)     default ''  not null comment '收件人名',
    phone      varchar(20)     default ''  not null comment '收件人手机号',
    province   varchar(20)     default '0' not null comment '省',
    city       varchar(20)     default '0' not null comment '市',
    area       varchar(20)     default '0' not null comment '区',
    address    varchar(120)    default ''  not null comment '地址',
    zip_code   varchar(50)                 null comment '邮编',
    default_in tinyint(1)      default 0   not null comment '0 不默认 1默认',
    tag        varchar(50)                 not null comment '0 家 1 学校 2公司',
    user_id    bigint unsigned default 0   not null comment '用户 id',
    create_at  bigint          default 0   not null comment '创建时间',
    update_at  bigint          default 0   not null comment '更新时间',
    deleted    tinyint(1)      default 0   not null comment '0 未删除 1 已删除'
)
    charset = utf8mb4;



CREATE TABLE `t_user_social`
(
    `id`              bigint(11)   NOT NULL AUTO_INCREMENT COMMENT '自增id',
    `social_id`       varchar(128) NOT NULL DEFAULT '' COMMENT '平台id',
    `social_union_id` varchar(128)          DEFAULT '' COMMENT '平台id',
    `type`            tinyint(2)   NOT NULL DEFAULT '0' COMMENT '平台类型 由客户端定义 0:未知,1:wechat,2:qq 3:微博',
    `nickname`        varchar(60)           DEFAULT '' COMMENT '昵称',
    `avatar`          varchar(255)          DEFAULT '' COMMENT '头像',
    `other`           text COMMENT '其他信息',
    `user_id`         bigint(20)   NOT NULL DEFAULT '0' COMMENT '用户id',
    `create_at`       bigint(20)   NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_at`       bigint(20)            DEFAULT '0' COMMENT '更新时间',
    `deleted`         tinyint(1)   NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;



CREATE TABLE `t_user_session`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `token`     varchar(255)                   DEFAULT NULL COMMENT 'token',
    `platform`  int(1)                         DEFAULT NULL COMMENT '平台类型',
    `device`    varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '设备',
    `user_id`   int(11)                        DEFAULT NULL COMMENT '用户id',
    `expire_at` bigint(20)                     DEFAULT NULL COMMENT '过期时间',
    `create_at` bigint(20)                     DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;

CREATE TABLE `t_user_relation`
(
    `id`        int(11) unsigned NOT NULL AUTO_INCREMENT,
    `parent`    bigint(20)                DEFAULT NULL COMMENT '最直接的上家id',
    `parent1`   bigint(20)                DEFAULT NULL COMMENT '二级上家',
    `parent2`   bigint(20)                DEFAULT NULL COMMENT '三级上家',
    `user_id`   bigint(20)                DEFAULT NULL COMMENT '用户ID',
    `remark`    varchar(255)              DEFAULT NULL COMMENT '备注',
    `create_at` bigint(20)                DEFAULT NULL,
    `update_at` bigint(20)                DEFAULT NULL,
    `deleted`   tinyint(1)       NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户 分销 关系.';