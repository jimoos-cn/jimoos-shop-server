--liquibase formatted sql

--changeset silentflower:2
-- 增加订单退款表
SET NAMES utf8mb4;

CREATE TABLE `t_order_refund`
(
    `id`            bigint(20)                               NOT NULL                AUTO_INCREMENT,
    `refund_num`     varchar(50)   COLLATE utf8mb4_unicode_ci NOT NULL                COMMENT '退款订单号',
    `order_num`      varchar(50)   COLLATE utf8mb4_unicode_ci NOT NULL                COMMENT '原订单号',
    `refund_status` tinyint(2)                               NOT NULL  DEFAULT '0'   COMMENT '退款状态',
    `order_amount`  decimal(20, 2)                           NOT NULL                COMMENT '原订单金额',
    `refund_amount` decimal(20, 2)                           NOT NULL                COMMENT '退款金额',
    `create_time`   bigint(20)                               NOT NULL                COMMENT '创建时间',
    `update_time`   bigint(20)                                                       COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
)ENGINE = InnoDB
 DEFAULT CHARSET = utf8mb4
 COLLATE = utf8mb4_unicode_ci COMMENT ='退款订单信息';
