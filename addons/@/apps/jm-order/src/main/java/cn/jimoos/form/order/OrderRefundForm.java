package cn.jimoos.form.order;

import lombok.Data;

/**
 * @author SiletFlower
 * @date 2021/7/27 13:05:37
 * @description 订单退款表单
 */
@Data
public class OrderRefundForm {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 订单编号
     */
    private String orderNum;

}
