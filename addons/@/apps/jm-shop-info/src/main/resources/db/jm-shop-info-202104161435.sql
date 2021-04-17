--liquibase formatted sql

--changeset kison:1

CREATE TABLE `t_shop_info`
(
    `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `shop_name`  varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '名称',
    `shop_intro` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '商城简介',
    `shop_phone` varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '管理员手机号',
    `shop_email` varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '管理员邮箱',
    `shop_about` text COLLATE utf8mb4_unicode_ci COMMENT '商城介绍',
    `create_at`  bigint(20)                               DEFAULT NULL,
    `update_at`  bigint(20)                               DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

INSERT INTO `t_shop_info` (`id`, `shop_name`, `shop_intro`, shop_phone, `shop_email`, `shop_about`,
                           `create_at`, `update_at`)
VALUES (1, '积墨开源商城', '致力于易于二次开发的商业开源商城', '', 'partner@jimoos.cn', '致力于易于二次开发的商业开源商城', 1618555099396, 1618555099396);
