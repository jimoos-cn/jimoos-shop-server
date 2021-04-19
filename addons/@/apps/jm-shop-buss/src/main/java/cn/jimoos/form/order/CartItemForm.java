package cn.jimoos.form.order;

import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-04-13 11:30.
 */
@Data
public class CartItemForm {
    private Long productId;
    private int number;
    private Long skuId;
}
