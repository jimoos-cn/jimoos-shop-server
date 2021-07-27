package cn.jimoos.form.order.be;

import lombok.Data;

/**
 * @author SiletFlower
 * @date 2021/7/27 16:02:37
 * @description
 */
@Data
public class BeRefundDeleteForm {
    /**
     * 订单退款表id
     */
    private Long id;
    /**
     * 订单编号
     */
    private String orderNum;
}
