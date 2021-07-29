package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author SiletFlower
 * @date 2021/7/26 15:21:31
 * @description  用于订单主动查询
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaySearchForm extends AbstractUserForm4L {
    /**
     * 商户订单号
     */
    private String outTradeNo;

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 3 微信支付
     */
    private Integer payType;

    /**
     * 用户标识（保证唯一）
     */
    private String openId;
}
