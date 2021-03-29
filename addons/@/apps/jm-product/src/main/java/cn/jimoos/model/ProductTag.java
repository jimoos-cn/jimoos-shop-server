package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品标签表
 *
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Data
@NoArgsConstructor
public class ProductTag {
    private Long id;

    /**
     * 标签名称
     */
    private String name;

    /**
     * 标签类型 0 普通标签 1 活动标签
     */
    private Byte type;

    /**
     * 主题颜色
     */
    private String color;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 0 未删除 1 已删除
     */
    private Boolean deleted;
}