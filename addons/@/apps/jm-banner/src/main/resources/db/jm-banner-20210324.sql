--liquibase formatted sql

--changeset keepcleargas:1

-- auto-generated definition
/*
 Navicat Premium Data Transfer

 Source Server         : yongjiu
 Source Server Type    : MySQL
 Source Server Version : 50719
 Source Host           : localhost:3306
 Source Schema         : j_odoo

 Target Server Type    : MySQL
 Target Server Version : 50719
 File Encoding         : 65001

 Date: 19/01/2021 17:02:54
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_banner`;
CREATE TABLE `t_banner`
(
    `id`          bigint(20) UNSIGNED                                           NOT NULL AUTO_INCREMENT,
    `title`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT 'banner标题',
    `img_url`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '广告图片',
    `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT '' COMMENT '位置描述',
    `position`    int(2)                                                        NOT NULL DEFAULT 0 COMMENT 'banner位置',
    `status`      int(2)                                                        NOT NULL DEFAULT 0 COMMENT '发布状态(0下架，1上架)',
    `sort`        int(6)                                                        NOT NULL DEFAULT 100 COMMENT '排序',
    `route_id`    bigint(20)                                                    NOT NULL COMMENT '路由类型 id',
    `target_id`   varchar(60) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '目标id',
    `paths`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '跳转路径',
    `color`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NULL     DEFAULT NULL COMMENT '主题颜色',
    `create_at`   bigint(20)                                                    NOT NULL DEFAULT 0 COMMENT '创建时间',
    `update_at`   bigint(20)                                                    NOT NULL DEFAULT 0 COMMENT '更新时间',
    `deleted`     tinyint(1)                                                    NOT NULL DEFAULT 0 COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = 'banner表'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- ----------------------------
-- Table structure for t_route
-- ----------------------------
DROP TABLE IF EXISTS `t_route`;
CREATE TABLE `t_route`
(
    `id`          bigint(20)                                             NOT NULL AUTO_INCREMENT,
    `description` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL     DEFAULT NULL COMMENT '描述',
    `type`        int(2)                                                 NOT NULL COMMENT '路由跳转类型',
    `route`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '路由跳转路径',
    `create_at`   bigint(20)                                             NOT NULL DEFAULT 0 COMMENT '创建时间',
    `update_at`   bigint(20)                                             NOT NULL DEFAULT 0 COMMENT '更新时间',
    `deleted`     tinyint(1)                                             NOT NULL DEFAULT 0 COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_bin COMMENT = 'banner路由表'
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

-- 插入默认数据 --

INSERT INTO `t_route`
VALUES (1, '跳转网页', 0, '%s', 1611136104720, 1611136104720, 0);