package cn.jimoos.form.order;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 购物车 下单表单
 *
 * @author :keepcleargas
 * @date :2021-04-13 11:29.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CartForm extends AbstractUserForm4L {
    private List<CartItemForm> cartItems;
    /**
     * 优惠券 ID
     */
    private Long discountRecordId;
}
