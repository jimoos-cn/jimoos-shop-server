package cn.jimoos.factory;
import cn.jimoos.entity.PaymentEntity;
import cn.jimoos.form.PayForm;
import cn.jimoos.repository.PaymentRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-20 17:56.
 */
@Component
public class PaymentFactory {
    @Resource
    PaymentRepository paymentRepository;

    public PaymentEntity create(PayForm payForm) {
        Long now = System.currentTimeMillis();
        PaymentEntity paymentEntity = new PaymentEntity(paymentRepository);
        paymentEntity.setTradeNo("");
        paymentEntity.setDeleted(false);
        paymentEntity.setSubject(payForm.getSubject());
        paymentEntity.setBody(payForm.getBody());
        paymentEntity.setOutTradeNo(payForm.getOrderNum());
        paymentEntity.setCreateAt(now);
        paymentEntity.setPaid(false);
        paymentEntity.setPay(payForm.getMoney());
        paymentEntity.setPayType(payForm.getPayType());
        return paymentEntity;
    }
}
