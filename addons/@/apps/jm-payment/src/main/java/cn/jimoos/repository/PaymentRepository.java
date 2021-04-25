package cn.jimoos.repository;

import cn.jimoos.dao.PaymentMapper;
import cn.jimoos.entity.PaymentEntity;
import cn.jimoos.model.Payment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-20 09:50.
 */
@Repository
public class PaymentRepository {
    @Resource
    PaymentMapper paymentMapper;

    /**
     * 根据 out trade no 获取支付对象
     *
     * @param outTradeNo out trade no
     * @return PaymentEntity
     */
    public PaymentEntity findByOutTradeNo(String outTradeNo) {
        return wrapper(paymentMapper.findOneByOutTradeNo(outTradeNo), false);
    }

    /**
     * 保存支付信息
     *
     * @param payment 支付信息
     * @return PaymentEntity
     */
    public PaymentEntity save(Payment payment) {
        if (payment != null && payment.getId() != null && payment.getId() > 0) {
            paymentMapper.updateByPrimaryKey(payment);
        } else {
            paymentMapper.insert(payment);
        }
        return wrapper(payment, false);
    }

    /**
     * 封装 repos
     * <p>
     * Payment的 entity wrapper方法
     *
     * @param payment  object
     * @param skipRepo true/false
     */
    private PaymentEntity wrapper(Payment payment, boolean skipRepo) {
        if (payment != null) {
            PaymentEntity paymentEntity = null;
            if (skipRepo) {
                paymentEntity = new PaymentEntity();
            } else {
                paymentEntity = new PaymentEntity(this);
            }
            BeanUtils.copyProperties(payment, paymentEntity);
            return paymentEntity;
        }
        return null;
    }
}
