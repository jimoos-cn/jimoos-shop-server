package cn.jimoos.vo.order;

import cn.jimoos.model.OrderCart;
import cn.jimoos.product.model.ProductItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author :keepcleargas
 * @date :2021-04-12 14:41.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class OrderCartVO extends OrderCart {
    ProductItem productItem;
}
