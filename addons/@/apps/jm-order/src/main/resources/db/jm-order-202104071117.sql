--liquibase formatted sql

--changeset kison:1

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
CREATE TABLE `t_order`
(
    `id`                  bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `order_num`           varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '订单编号',
    `subject`             varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '订单标题',
    `order_type`          varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '订单类别',
    `item_amount`         int(11)                                  DEFAULT '1' COMMENT '商品数量',
    `total_price`         decimal(20, 2)                           DEFAULT NULL COMMENT '总价格',
    `total_fee`           decimal(20, 2)                           DEFAULT NULL COMMENT '费用',
    `total_product_price` decimal(20, 2)                           DEFAULT NULL COMMENT '商品总价',
    `total_discount`      decimal(20, 2)                           DEFAULT NULL COMMENT '总优惠',
    `real_pay`            decimal(20, 2)                           DEFAULT NULL COMMENT '实际支付',
    `user_id`             bigint(20)                               DEFAULT NULL COMMENT '购买者ID',
    `comment`             varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '购买备注',
    `status`              tinyint(2)                               DEFAULT '0' COMMENT '订单状态',
    `refund`              tinyint(1)                               DEFAULT '0' COMMENT '是否发生退单',
    `rate`                tinyint(1)                               DEFAULT '0' COMMENT '是否发生评价',
    `extra`               varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '额外字段-用于特殊业务场景下的信息的储存',
    `create_at`           bigint(20)                               DEFAULT NULL,
    `update_at`           bigint(20)                               DEFAULT NULL,
    `deleted`             tinyint(1)                               DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单信息';

-- ----------------------------
-- Table structure for t_order_cart
-- ----------------------------
CREATE TABLE `t_order_cart`
(
    `id`         bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `product_id` bigint(20)          NOT NULL COMMENT 'uid',
    `uid`        bigint(20)          NOT NULL,
    `sku_id`     bigint(20)                   DEFAULT NULL COMMENT 'SKU ID（可选）',
    `num`        int(11)             NOT NULL DEFAULT '1' COMMENT '商品数量',
    `checked`    tinyint(1)          NOT NULL DEFAULT '0' COMMENT '是否选中',
    `create_at`  bigint(20)          NOT NULL COMMENT '创建时间',
    `update_at`  bigint(20)          NOT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
CREATE TABLE `t_order_item`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `product_id`    bigint(20)                               DEFAULT NULL COMMENT '商品ID',
    `product_name`  varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '商品名称',
    `product_cover` varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '商品封面',
    `product_type`  varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '商品类别',
    `sku_id`        bigint(20)                               DEFAULT NULL COMMENT 'SKU ID（可选）',
    `sku_name`      varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT 'SKU 名称（可选）',
    `sku_cover`     varchar(255) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT 'SKU 封面（可选）',
    `num`           int(11)                                  DEFAULT '1' COMMENT '订购数量',
    `price`         decimal(20, 2)                           DEFAULT NULL COMMENT '总费用',
    `fee`           decimal(20, 2)                           DEFAULT NULL COMMENT '费用',
    `product_price` decimal(20, 2)                           DEFAULT NULL COMMENT '商品价格',
    `discount`      decimal(20, 2)                           DEFAULT NULL COMMENT '优惠金额',
    `real_pay`      decimal(20, 2)                           DEFAULT NULL COMMENT '实际应支付',
    `order_id`      bigint(20)                               DEFAULT NULL COMMENT '订单ID',
    `extra`         varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '额外字段-用于特殊商品信息的储存',
    `create_at`     bigint(20)                               DEFAULT NULL,
    `update_at`     bigint(20)                               DEFAULT NULL,
    `deleted`       tinyint(1)                               DEFAULT '0',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='商品详情';

-- ----------------------------
-- Table structure for t_order_item_discount
-- ----------------------------
CREATE TABLE `t_order_item_discount`
(
    `id`            bigint(20) unsigned                    NOT NULL AUTO_INCREMENT,
    `type`          varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '优惠类型',
    `des`           varchar(255) COLLATE utf8mb4_unicode_ci         DEFAULT NULL COMMENT '优惠描述',
    `discount_id`   bigint(20)                                      DEFAULT NULL COMMENT '优惠ID',
    `discount`      decimal(20, 2)                         NOT NULL COMMENT '优惠金额',
    `need_return`   tinyint(1)                                      DEFAULT '0' COMMENT '是否需要退还',
    `return_type`   varchar(50) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '退回事件类型',
    `return_status` tinyint(1)                                      DEFAULT '0' COMMENT '退还状态',
    `return_at`     bigint(20)                                      DEFAULT NULL COMMENT '退还执行时间',
    `order_id`      bigint(20)                             NOT NULL COMMENT '订单ID',
    `order_item_id` bigint(20)                             NOT NULL COMMENT '子订单编号',
    `extras`        varchar(1024) COLLATE utf8mb4_unicode_ci        DEFAULT NULL COMMENT '其它信息',
    `created`       bigint(20)                                      DEFAULT NULL,
    `updated`       bigint(20)                                      DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单优惠';

-- ----------------------------
-- Table structure for t_order_item_fee
-- ----------------------------
CREATE TABLE `t_order_item_fee`
(
    `id`            bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `type`          varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '费用类别',
    `fee_id`        bigint(20)                               DEFAULT NULL COMMENT '费用ID',
    `fee`           decimal(20, 0)                           DEFAULT NULL COMMENT '费用',
    `need_return`   tinyint(1)                               DEFAULT '0' COMMENT '是否需要退还',
    `return_type`   varchar(50) COLLATE utf8mb4_unicode_ci   DEFAULT NULL COMMENT '退回事件类型',
    `return_status` tinyint(1)                               DEFAULT '0' COMMENT '退还状态',
    `return_at`     bigint(20)                               DEFAULT NULL COMMENT '退还执行事件',
    `order_id`      bigint(20)                               DEFAULT NULL COMMENT '订单ID',
    `order_item_id` bigint(20)          NOT NULL COMMENT '子订单编号',
    `extras`        varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '额外信息',
    `create_at`     bigint(20)                               DEFAULT NULL,
    `updated`       bigint(20)                               DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单其它费用';

-- ----------------------------
-- Table structure for t_order_remind_delivery
-- ----------------------------
CREATE TABLE `t_order_remind_delivery`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `order_id`  bigint(20) NOT NULL COMMENT '订单号',
    `uid`       bigint(20) NOT NULL COMMENT '用户id',
    `create_at` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_at` bigint(20) NOT NULL DEFAULT '0' COMMENT '更新时间',
    `deleted`   tinyint(1) NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci
  ROW_FORMAT = DYNAMIC COMMENT ='订单 提醒发货';

-- ----------------------------
-- Table structure for t_order_ship
-- ----------------------------
CREATE TABLE `t_shipment`
(
    `id`           int(11) unsigned NOT NULL AUTO_INCREMENT,
    `name`         varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '收货人姓名',
    `phone`        varchar(20) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '收货人电话',
    `province`     varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '省',
    `city`         varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '市',
    `area`         varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '县',
    `address`      varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '地址',
    `zip_code`     varchar(20) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '邮编',
    `comment`      varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '发货备注',
    `ship_code`    varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '快递单号',
    `express`      varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '快递名称',
    `express_code` varchar(50) COLLATE utf8mb4_unicode_ci  DEFAULT NULL COMMENT '快递编码',
    `status`       tinyint(2)                              DEFAULT '0' COMMENT '发货状态',
    `type`         tinyint(2)                              DEFAULT '0' COMMENT ' 类型',
    `user_id`      bigint(20)                              DEFAULT NULL COMMENT 'userId',
    `out_trade_no` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '外部 编号',
    `create_at`    bigint(20)                              DEFAULT NULL,
    `update_at`    bigint(20)                              DEFAULT '0',
    `deleted`      tinyint(1)                              DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='订单配送单';

SET FOREIGN_KEY_CHECKS = 1;



