package cn.jimoos.vo;

import java.math.BigDecimal;

/**
 * @author :keepcleargas
 * @date :2021-04-13 11:11.
 */
public interface IOrderPre {
    /**
     * 获取 总优惠价格
     *
     * @return BigDecimal
     */
    BigDecimal getTotalDiscount();

    /**
     * 获取 总价格
     *
     * @return BigDecimal
     */
    BigDecimal getTotalPrice();

    /**
     * 获取 合计价格
     *
     * @return BigDecimal
     */
    BigDecimal getRealPay();
}
