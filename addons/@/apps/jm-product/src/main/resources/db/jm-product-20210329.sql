--liquibase formatted sql

--changeset kison:1


-- Table structure for t_product
-- ----------------------------
CREATE TABLE `t_product`
(
    `id`          bigint(20)                               NOT NULL AUTO_INCREMENT COMMENT '商品(SPU) ID',
    `name`        varchar(255) COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT 'spu名称',
    `category_id` bigint(20)                               NOT NULL DEFAULT '0' COMMENT '分类ID',
    `text`        longtext COLLATE utf8mb4_unicode_ci      NOT NULL COMMENT 'spu描述',
    `cover`       varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '商品封面',
    `video_url`   text COLLATE utf8mb4_unicode_ci COMMENT '商品视频 多个视频逗号分隔',
    `banner_urls` text COLLATE utf8mb4_unicode_ci          NOT NULL COMMENT 'banner图片 多个图片逗号分隔',
    `sort`        int(11)                                  NOT NULL COMMENT '排序',
    `fake_sales`  int(11)                                  NOT NULL DEFAULT '0' COMMENT '运营销量',
    `status`      tinyint(4)                               NOT NULL COMMENT '0 未上架 1待审核 2 上架中 3 未通过 ',
    `type`        tinyint(4)                               NOT NULL COMMENT '0 普通商品',
    `merchant_id` bigint(20)                               NOT NULL DEFAULT '0' COMMENT '商家 ID',
    `create_at`   bigint(20)                               NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_at`   bigint(20)                               NOT NULL DEFAULT '0' COMMENT '更新时间',
    `deleted`     tinyint(1)                               NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='商品(spu)';

-- ----------------------------
-- Table structure for t_product_attr
-- ----------------------------
CREATE TABLE `t_product_attr`
(
    `id`          bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '销售属性ID',
    `name`        varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '销售属性名称',
    `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '销售属性描述',
    `merchant_id` bigint(20)                              NOT NULL DEFAULT '0' COMMENT '商家 ID',
    `parent_id`   bigint(20)                              NOT NULL COMMENT 'parent_id 如果商家的attr 和 平台的 attr 重名',
    `create_at`   bigint(20)                              NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_at`   bigint(20)                              NOT NULL DEFAULT '0' COMMENT '更新时间',
    `deleted`     tinyint(1)                              NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='销售属性表';

-- ----------------------------
-- Table structure for t_product_attr_value
-- ----------------------------
CREATE TABLE `t_product_attr_value`
(
    `id`          bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '销售属性值ID',
    `name`        varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '销售属性值',
    `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '销售属性值描述',
    `sort`        int(11)                                 NOT NULL COMMENT '排序',
    `merchant_id` bigint(20)                              NOT NULL DEFAULT '0' COMMENT '商家 ID',
    `attr_id`     bigint(20)                              NOT NULL DEFAULT '0' COMMENT '销售属性ID',
    `create_at`   bigint(20)                              NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_at`   bigint(20)                              NOT NULL DEFAULT '0' COMMENT '更新时间',
    `deleted`     tinyint(1)                              NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='销售属性值';

-- ----------------------------
-- Table structure for t_product_category
-- ----------------------------
CREATE TABLE `t_product_category`
(
    `id`          bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '分类ID',
    `pid`         bigint(20)                              NOT NULL DEFAULT '0' COMMENT '父ID',
    `name`        varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '分类名称',
    `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '分类描述',
    `img_url`     varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '分类图片',
    `sort`        int(11)                                 NOT NULL COMMENT '排序',
    `create_at`   bigint(20)                              NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_at`   bigint(20)                              NOT NULL DEFAULT '0' COMMENT '更新时间',
    `deleted`     tinyint(1)                              NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='类别表';

-- ----------------------------
-- Table structure for t_product_sku
-- ----------------------------
CREATE TABLE `t_product_sku`
(
    `id`             bigint(20)                               NOT NULL AUTO_INCREMENT COMMENT 'SKU ID',
    `attr_value_ids` varchar(512) COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '销售属性值{attr_value_id},{attr_value_id} 多个销售属性值逗号分隔',
    `cover`          varchar(1024) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '封面',
    `price`          decimal(38, 2)                           NOT NULL DEFAULT '0.00' COMMENT '价格',
    `show_price`     decimal(38, 2)                                    DEFAULT '0.00' COMMENT '展示价',
    `product_id`     bigint(20)                               NOT NULL DEFAULT '0' COMMENT 'SPU ID',
    `merchant_id`    bigint(20)                               NOT NULL DEFAULT '0' COMMENT '商家 ID',
    `create_at`      bigint(20)                               NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_at`      bigint(20)                               NOT NULL DEFAULT '0' COMMENT '更新时间',
    `deleted`        tinyint(1)                               NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='sku表';

-- ----------------------------
-- Table structure for t_product_sku_attr_map
-- ----------------------------
CREATE TABLE `t_product_sku_attr_map`
(
    `id`              bigint(20)                              NOT NULL AUTO_INCREMENT COMMENT '自增ID',
    `attr_id`         bigint(20)                              NOT NULL DEFAULT '0' COMMENT '销售属性ID',
    `attr_name`       varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '销售属性名称',
    `attr_value_id`   bigint(20)                              NOT NULL DEFAULT '0' COMMENT '销售属性值ID',
    `attr_value_name` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '0' COMMENT '销售属性值',
    `sku_id`          bigint(20)                              NOT NULL DEFAULT '0' COMMENT 'SKU ID',
    `product_id`      bigint(20)                              NOT NULL DEFAULT '0' COMMENT 'SPU ID',
    `merchant_id`     bigint(20)                              NOT NULL DEFAULT '0' COMMENT '商家 ID',
    `create_at`       bigint(20)                              NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_at`       bigint(20)                              NOT NULL DEFAULT '0' COMMENT '更新时间',
    `deleted`         tinyint(1)                              NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='关联关系冗余表';

-- ----------------------------
-- Table structure for t_product_tag
-- ----------------------------
CREATE TABLE `t_product_tag`
(
    `id`        bigint(20)                              NOT NULL AUTO_INCREMENT,
    `name`      varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '标签名称',
    `type`      tinyint(2) unsigned                     NOT NULL COMMENT '标签类型 0 普通标签 1 活动标签',
    `color`     varchar(50) COLLATE utf8mb4_unicode_ci           DEFAULT NULL COMMENT '主题颜色',
    `create_at` bigint(20)                              NOT NULL DEFAULT '0' COMMENT '创建时间',
    `update_at` bigint(20)                              NOT NULL DEFAULT '0' COMMENT '更新时间',
    `deleted`   tinyint(1)                              NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='商品标签表';

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `r_product_tag`
(
    `id`         bigint(20) NOT NULL AUTO_INCREMENT,
    `product_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品 id',
    `tag_id`     bigint(20) NOT NULL DEFAULT '0' COMMENT '标签 id',
    `create_at`  bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='商品标签绑定表';