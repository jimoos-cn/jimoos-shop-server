package cn.jimoos.order.dic;

import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-04-08 15:01.
 */
@Data
public class DiscountType {
    /**
     * code 比如:COUPON
     */
    private String code;
    /**
     * 描述 比如:优惠券
     */
    private String des;
    /**
     * 是否需要退回
     */
    private boolean needReturn;

    public DiscountType() {
    }

    public DiscountType(String code, String des, boolean needReturn) {
        this.code = code;
        this.des = des;
        this.needReturn = needReturn;
    }
}
