package cn.jimoos.vo;

import cn.jimoos.model.Product;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * @author :keepcleargas
 * @date :2021-05-11 11:21.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSimpleVO extends Product {
    /**
     * 显示 SKU 里的最低价
     */
    private BigDecimal price;
    /**
     * 显示价
     */
    private BigDecimal showPrice;
}
