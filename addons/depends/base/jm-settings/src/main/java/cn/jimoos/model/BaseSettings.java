package cn.jimoos.model;

import lombok.Data;

/**
 * 设置表
 *
 * @author :keepcleargas
 * @date :2020-11-16 13:48.
 */
@Data
public class BaseSettings {
    private Integer id;

    /**
     * 关键词, PAY.ALI PAY.MP
     */
    private String keyword;

    /**
     * 配置内容
     */
    private String content;

    private Long created;

    private Long updated;

    private Integer deleted;
}