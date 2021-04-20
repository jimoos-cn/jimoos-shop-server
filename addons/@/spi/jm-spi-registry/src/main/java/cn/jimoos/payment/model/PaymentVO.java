package cn.jimoos.payment.model;

import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-04-20 17:40.
 */
@Data
public class PaymentVO {
    private String outTradeNo;
    private String subject;
    private String payToken;
    private Integer payType;

    public PaymentVO() {
    }

    public PaymentVO(String outTradeNo, String subject, String payToken, Integer payType) {
        this.outTradeNo = outTradeNo;
        this.subject = subject;
        this.payToken = payToken;
        this.payType = payType;
    }
}
