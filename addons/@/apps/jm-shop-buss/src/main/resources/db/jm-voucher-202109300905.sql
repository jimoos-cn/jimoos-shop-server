--liquibase formatted sql

--changeset silentflower:1
-- 增加订单退款表
SET NAMES utf8mb4;

CREATE TABLE `t_order_voucher`
(
    `id`        bigint(20) UNSIGNED                                            NOT NULL AUTO_INCREMENT,
    `order_id`  bigint(20)                                                     NULL DEFAULT NULL COMMENT '订单ID',
    `status`    tinyint(4)                                                     NULL DEFAULT NULL COMMENT '状态 0审核中 1通过 -1审核失败',
    `content`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT '' COMMENT '凭证内容',
    `picture`   varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '凭证图片',
    `reason`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NULL DEFAULT NULL COMMENT '失败原因',
    `money`     decimal(14, 2)                                                 NULL DEFAULT NULL COMMENT '应付金额',
    `create_at` bigint(20)                                                     NULL DEFAULT NULL COMMENT '创建时间',
    `update_at` bigint(20)                                                     NULL DEFAULT NULL COMMENT '更新时间',
    `deleted`   tinyint(1)                                                     NULL DEFAULT 0 COMMENT '删除标志',
    PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB 
    CHARACTER SET = utf8mb4 
    COLLATE = utf8mb4_unicode_ci 
    COMMENT = '订单支付凭证'
