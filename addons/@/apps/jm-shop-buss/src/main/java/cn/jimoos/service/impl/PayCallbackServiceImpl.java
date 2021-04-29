package cn.jimoos.service.impl;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.entity.OrderEntity;
import cn.jimoos.entity.PaymentEntity;
import cn.jimoos.repository.OrderRepository;
import cn.jimoos.repository.PaymentRepository;
import cn.jimoos.service.PayCallbackService;
import cn.jimoos.utils.OutTradeNoEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-29 10:58.
 */
@Service
@Slf4j
public class PayCallbackServiceImpl implements PayCallbackService {
    @Resource
    private OrderRepository orderRepository;
    @Resource
    private PaymentRepository paymentRepository;

    @Override
    public void wxPayCallback(String outTradeNo, String extras, String tradeNo, String channel) {
        handleNotify(OutTradeNoEncoder.decodeOutTradeNo(outTradeNo), extras, tradeNo, channel);
    }


    /**
     * 处理回调
     *
     * @param outTradeNo
     * @param extras
     * @param tradeNo
     * @param channel
     */
    private void handleNotify(String outTradeNo, String extras, String tradeNo, String channel) {
        try {
            OrderEntity orderEntity = orderRepository.findByNum(outTradeNo);

            if (!orderEntity.isPaid()) {
                orderEntity.setPaid();

                orderRepository.saveStatus(orderEntity);

                PaymentEntity payment = paymentRepository.findByOutTradeNo(outTradeNo);
                if (payment != null) {
                    payment.paid(tradeNo, channel, extras);
                    paymentRepository.save(payment);
                } else {
                    log.error("支付信息:{} 丢失了", outTradeNo);
                }
            } else {
                log.info("订单 {} 已经支付过了", outTradeNo);
            }
        } catch (BussException e) {
            log.error("支付回调订单找不到 {}", outTradeNo);
        }
    }
}
