package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品标签绑定表
 *
 * @author :keepcleargas
 * @date :2021-03-29 19:52.
 */
@Data
@NoArgsConstructor
public class RProductTag {
    private Long id;

    /**
     * 商品 id
     */
    private Long productId;

    /**
     * 标签 id
     */
    private Long tagId;

    /**
     * 创建时间
     */
    private Long createAt;
}