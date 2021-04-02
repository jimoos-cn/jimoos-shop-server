--liquibase formatted sql

--changeset kison:2

-- 用户商品收藏表
-- ----------------------------

CREATE TABLE `t_user_product_collection`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id`    bigint(20) NOT NULL COMMENT '用户id',
    `product_id` bigint(20) NOT NULL COMMENT '商品id',
    `create_at`  bigint(20) NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4;