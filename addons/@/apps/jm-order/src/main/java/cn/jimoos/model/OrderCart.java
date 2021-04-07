package cn.jimoos.model;

import lombok.Data;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2021/4/7 11:35
 */

@Data
public class OrderCart {
    private Long id;

    /**
     * uid
     */
    private Long productId;

    private Long uid;

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