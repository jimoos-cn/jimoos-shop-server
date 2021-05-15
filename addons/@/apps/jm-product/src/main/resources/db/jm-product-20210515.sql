--liquibase formatted sql

--changeset kison:3
-- 销售属性 单品添加
-- ----------------------------

INSERT INTO `t_product_attr` (`id`, `name`, `description`, `merchant_id`, `parent_id`, `create_at`, `update_at`,
                              `deleted`)
VALUES (1, '单品', '单品', 0, 0, 1611730159418, 0, 0);


INSERT INTO `t_product_attr_value` (`id`, `name`, `description`, `sort`, `merchant_id`, `attr_id`, `create_at`,
                                    `update_at`, `deleted`)
VALUES (1, '单品', '单品', 1, 0, 1, 1611730159418, 0, 0);
