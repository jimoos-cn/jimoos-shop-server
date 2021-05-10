package cn.jimoos.vo;

import cn.jimoos.model.Product;
import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-05-10 09:50.
 */
@Data
public class ProductCollectVO {
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 商品id
     */
    private Long productId;

    /**
     * 创建时间
     */
    private Long createAt;

    Product product;
}
