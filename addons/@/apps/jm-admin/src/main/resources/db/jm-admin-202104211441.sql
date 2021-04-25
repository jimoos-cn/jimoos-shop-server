--liquibase formatted sql

--changeset kison:1

CREATE TABLE `t_admin`
(
    `id`                 bigint(20)                              NOT NULL AUTO_INCREMENT,
    `username`           varchar(50) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '管理员名',
    `encrypted_password` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
    `salt`               varchar(64) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '密码',
    `ban`                tinyint(1)                              NOT NULL COMMENT '0 不禁止登陆 1 禁止登陆',
    `create_at`          bigint(20)                                       DEFAULT '0' COMMENT '创建时间',
    `deleted`            tinyint(1)                              NOT NULL DEFAULT '0' COMMENT '是否删除 0表示不删除 1表示删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = DYNAMIC COMMENT ='后台管理账户';

LOCK TABLES `t_admin` WRITE;
/*!40000 ALTER TABLE `t_admin`
    DISABLE KEYS */;

INSERT INTO `t_admin` (`id`, `username`, `encrypted_password`, `salt`, `ban`, `create_at`, `deleted`)
VALUES (1, 'admin', '$2a$10$ItAYf8qB77saLeSYLkFfTeKlOVm266sZ4CQxC8QVI.EqPo0f3St6q', '$2a$10$ItAYf8qB77saLeSYLkFfTe', 0,
        1603688879332, 0);
#     /*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table t_admin_login_log
# ------------------------------------------------------------

CREATE TABLE `t_admin_login_log`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `admin_id`  bigint(20) NOT NULL                                            DEFAULT '0' COMMENT '管理员id',
    `ip`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  DEFAULT '' COMMENT '管理员登录ip',
    `agent`     varchar(2048) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '请求头',
    `create_at` bigint(20)                                                     DEFAULT '0' COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  ROW_FORMAT = DYNAMIC;



# Dump of table t_admin_token
# ------------------------------------------------------------

CREATE TABLE `t_admin_token`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `admin_id`  bigint(20) NOT NULL COMMENT '管理员id',
    `token`     varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '管理员的token',
    `ip`        varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '管理员登录ip',
    `create_at` bigint(20)                              DEFAULT '0' COMMENT '创建时间',
    `expired`   bigint(20)                              DEFAULT '0' COMMENT '过期时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = DYNAMIC COMMENT ='后台管理员 token';