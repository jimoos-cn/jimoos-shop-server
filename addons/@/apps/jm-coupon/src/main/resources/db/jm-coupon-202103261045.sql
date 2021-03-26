--liquibase formatted sql

--changeset keepcleargas:1

CREATE TABLE `t_coupon`
(
    `id`                   bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `reduce_money`         decimal(10, 2) DEFAULT NULL COMMENT '减的金额',
    `full_money`           decimal(10, 2) DEFAULT NULL COMMENT '满的金额',
    `des`                  varchar(50)    DEFAULT NULL COMMENT '优惠券描述',
    `code`                 varchar(128)   DEFAULT NULL COMMENT '兑换码',
    `effective_start_time` bigint(20)     DEFAULT NULL COMMENT '有效时间开始',
    `effective_end_time`   bigint(20)     DEFAULT NULL COMMENT '有效时间结束',
    `start_time`           bigint(11)     DEFAULT NULL COMMENT '领券开始时间',
    `end_time`             bigint(11)     DEFAULT NULL COMMENT '领券截至时间',
    `valid_time`           int(11)        DEFAULT NULL COMMENT '有效时间 秒 为单位',
    `total_num`            int(11)        DEFAULT NULL COMMENT '一共多少张',
    `remain_num`           int(11)        DEFAULT NULL COMMENT '剩余发放数',
    `sort`                 int(11)        DEFAULT NULL COMMENT '排序',
    `status`               tinyint(2)     DEFAULT '0' COMMENT '1 在线 0 下架',
    `merchant_id`          bigint(20)     DEFAULT '0',
    `create_at`            bigint(20)     DEFAULT NULL COMMENT '创建时间',
    `update_at`            bigint(20)     DEFAULT NULL COMMENT '更新时间',
    `deleted`              int(11)        DEFAULT NULL COMMENT '0 删除 1未删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='优惠券表';

CREATE TABLE `t_coupon_record`
(
    `id`        bigint(20) unsigned NOT NULL AUTO_INCREMENT,
    `coupon_id` bigint(20) DEFAULT NULL COMMENT '优惠id',
    `user_id`   bigint(20) DEFAULT NULL COMMENT '用户',
    `status`    tinyint(1) DEFAULT '0' COMMENT '0未使用1已使用',
    `expired`   bigint(20) DEFAULT '20' COMMENT '过期时间',
    `create_at` bigint(20) DEFAULT NULL COMMENT '创建时间',
    `update_at` bigint(20) DEFAULT NULL COMMENT '更新时间',
    `deleted`   tinyint(1) DEFAULT '0' COMMENT '0 未删除 1删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户优惠券表';