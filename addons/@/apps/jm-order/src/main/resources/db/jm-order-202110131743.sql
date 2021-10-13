--liquibase formatted sql

--changeset yyx:3
create table t_order_voucher
(
    id        bigint unsigned auto_increment
        primary key,
    order_id  bigint                   null comment '订单ID',
    status    tinyint                  null comment '状态 0审核中 1通过 -1审核失败',
    content   varchar(255)  default '' null comment '凭证内容',
    picture   varchar(1024) default '' null comment '凭证图片',
    reason    varchar(100)             null comment '失败原因',
    money     decimal(14, 2)           null comment '应付金额',
    create_at bigint                   null comment '创建时间',
    update_at bigint                   null comment '更新时间',
    deleted   tinyint(1)    default 0  null comment '删除标志'
)
    comment '订单支付凭证' collate = utf8mb4_unicode_ci;



