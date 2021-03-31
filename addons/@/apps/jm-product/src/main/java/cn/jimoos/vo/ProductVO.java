package cn.jimoos.vo;

import cn.jimoos.model.Product;
import cn.jimoos.model.ProductCategory;
import cn.jimoos.model.ProductSku;
import cn.jimoos.model.ProductTag;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
    private List<ProductSku> productSkus;

    private ProductCategory category;

    private List<ProductTag> tags;
}
