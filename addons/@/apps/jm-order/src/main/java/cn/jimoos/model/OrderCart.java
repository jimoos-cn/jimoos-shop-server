package cn.jimoos.model;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :keepcleargas
 * @date :2021-04-07 18:19.
 */

@Data
@NoArgsConstructor
public class OrderCart {
    private Long id;

    /**
     * uid
     */
    private Long productId;

    private Long userId;

    /**
     * SKU ID（可选）
     */
    private Long skuId;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 是否选中
     */
    private Boolean checked;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 更新时间
     */
    private Long updateAt;
}