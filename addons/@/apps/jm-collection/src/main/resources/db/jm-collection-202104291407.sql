--liquibase formatted sql

--changeset kison:1

CREATE TABLE `t_collection`
(
    `id`        bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `title`     varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '标题',
    `sub_title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '子标题',
    `recommend` tinyint(1)                              DEFAULT '0' COMMENT '推荐 0 未推荐 1 推荐',
    `sort`      int(11)                                 DEFAULT '100' COMMENT '排序',
    `status`    tinyint(1)                              DEFAULT '0' COMMENT '上下架状态',
    `type`      int(11)                                 DEFAULT NULL COMMENT '类别',
    `create_at` bigint(20)                              DEFAULT NULL,
    `update_at` bigint(20)                              DEFAULT NULL,
    `deleted`   tinyint(1)                              DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

CREATE TABLE `r_collection_product`
(
    `id`            bigint(11) unsigned NOT NULL AUTO_INCREMENT,
    `product_id`    bigint(20) DEFAULT NULL COMMENT '目标商品 ID',
    `collection_id` bigint(20) DEFAULT NULL COMMENT '集合 ID',
    `sort`          int(11)    DEFAULT '100',
    `create_at`     bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

