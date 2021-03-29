package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 销售属性表
 *
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Data
@NoArgsConstructor
public class ProductAttr {
    /**
     * 销售属性ID
     */
    private Long id;

    /**
     * 销售属性名称
     */
    private String name;

    /**
     * 销售属性描述
     */
    private String description;

    /**
     * 商家 ID
     */
    private Long merchantId;

    /**
     * parent_id 如果商家的attr 和 平台的 attr 重名
     */
    private Long parentId;

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