package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractUserForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-20 17:56.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class PayForm extends AbstractUserForm4L {
    private String orderNum;

    private Integer payType;

    private String subject;

    private String body;

    private BigDecimal money;

    private Map<String, Object> extras = new HashMap<>();

    public PayForm() {
    }

    public PayForm(String orderNum, Integer payType, String subject, String body, BigDecimal money, Map<String, Object> extras) {
        this.orderNum = orderNum;
        this.payType = payType;
        this.subject = subject;
        this.body = body;
        this.money = money;
        this.extras = extras;
    }
}
