package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类别表
 *
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Data
@NoArgsConstructor
public class ProductCategory {
    /**
     * 分类ID
     */
    private Long id;

    /**
     * 父ID
     */
    private Long pid;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 分类图片
     */
    private String imgUrl;

    /**
     * 排序
     */
    private Integer sort;

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