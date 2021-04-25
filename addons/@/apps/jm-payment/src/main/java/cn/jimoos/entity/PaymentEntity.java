package cn.jimoos.entity;

import cn.jimoos.form.PayForm;
import cn.jimoos.model.Payment;
import cn.jimoos.repository.PaymentRepository;
import lombok.EqualsAndHashCode;

/**
 * @author :keepcleargas
 * @date :2021-04-20 17:56.
 */
@EqualsAndHashCode(callSuper = true)
public class PaymentEntity extends Payment {
    private PaymentRepository paymentRepository;

    public PaymentEntity() {
    }

    public PaymentEntity(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * 支付成功
     *
     * @param tradeNo 交易号
     * @param channel 支付渠道
     * @param payType 支付类别
     * @param extras  额外的信息
     */
    public void paid(String tradeNo, String channel, Integer payType, String extras) {
        this.setPaid(true);
        this.setTradeNo(tradeNo);
        this.setPaidChannel(channel);
        this.setPayType(payType);
        this.setPayAt(System.currentTimeMillis());
    }

    /**
     * 重新选择 支付方式
     *
     * @param payForm 支付表单
     */
    public void repay(PayForm payForm) {
        this.setPayType(payForm.getPayType());
        this.setUpdateAt(System.currentTimeMillis());
    }
}
