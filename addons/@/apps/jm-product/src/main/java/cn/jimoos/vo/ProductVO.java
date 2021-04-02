package cn.jimoos.vo;

import cn.jimoos.model.Product;
import cn.jimoos.model.ProductCategory;
import cn.jimoos.model.ProductTag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-30 20:35.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductVO extends Product {
    /**
     * sku列表
     */
    private List<ProductSkuVO> productSkuVos;

    private ProductCategory category;

    private List<ProductTag> tags;
    /**
     * 是否收藏了
     */
    private Boolean collect;
    /**
     * 显示 SKU 里的最低价
     */
    private BigDecimal price;
    /**
     * 显示价
     */
    private BigDecimal showPrice;

    public ProductVO() {
        this.collect = false;
    }
}
