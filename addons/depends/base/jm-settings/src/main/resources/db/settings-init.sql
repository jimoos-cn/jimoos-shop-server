--liquibase formatted sql

--changeset keepcleargas:1

-- auto-generated definition
create table t_base_settings
(
    id      int(11) unsigned auto_increment
        primary key,
    keyword varchar(255)     null comment '关键词, PAY.ALI PAY.MP',
    content text             null comment '配置内容',
    created bigint           null,
    updated bigint           null,
    deleted int(1) default 0 null
)comment '设置表';

