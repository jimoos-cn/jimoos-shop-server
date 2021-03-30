package cn.jimoos.vo;

import cn.jimoos.model.ProductSkuAttrMap;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-30 20:39.
 */
@Data
public class ProductSkuVO {
    private Long id;
    /**
     * 销售属性键值对
     */
    private List<ProductSkuAttrMap> attrs;

    /**
     * 封面
     */
    private String cover;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 展示价
     */
    private BigDecimal showPrice;
}
