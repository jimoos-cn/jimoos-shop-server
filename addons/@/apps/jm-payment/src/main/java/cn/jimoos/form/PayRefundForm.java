package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author SiletFlower
 * @date 2021/7/26 16:34:41
 * @description 退款表单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayRefundForm extends AbstractAdminForm4L {
    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 退款金额
     */
    private BigDecimal refundMoney;

    /**
     * 原订单金额
     */
    private BigDecimal money;

    /**
     * 3 微信支付
     */
    private Integer payType;
}
