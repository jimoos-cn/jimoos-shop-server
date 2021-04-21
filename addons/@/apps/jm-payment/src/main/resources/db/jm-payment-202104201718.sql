--liquibase formatted sql

--changeset kison:1

CREATE TABLE `t_payment`
(
    `id`           bigint(20)     NOT NULL AUTO_INCREMENT,
    `out_trade_no` varchar(128)   NOT NULL COMMENT '商户网站唯一订单号',
    `subject`      varchar(255)            DEFAULT NULL COMMENT '支付标题',
    `body`         varchar(255)            DEFAULT NULL COMMENT '支付内容',
    `pay`          decimal(38, 2) NOT NULL COMMENT '实付 单位：元',
    `pay_type`     tinyint(2)     NOT NULL COMMENT '0 钱包支付 1 转账 2支付宝支付 3 微信支付 ',
    `trade_no`     varchar(128)   NOT NULL DEFAULT '' COMMENT '交易流水号 支付宝 微信 等',
    `paid`         tinyint(1)     NOT NULL COMMENT '0 未支付 1 已支付',
    `paid_channel` varchar(255)            DEFAULT NULL COMMENT '支付方式 如招商银行',
    `pay_at`       bigint(20)              DEFAULT NULL COMMENT '支付时间',
    `create_at`    bigint(20)              DEFAULT '0' COMMENT '创建时间',
    `update_at`    bigint(20)              DEFAULT '0' COMMENT '更新时间',
    `deleted`      tinyint(1)     NOT NULL DEFAULT '0' COMMENT '0 未删除 1 已删除',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='支付表';

