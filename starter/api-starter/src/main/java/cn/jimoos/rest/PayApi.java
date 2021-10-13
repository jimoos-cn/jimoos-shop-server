package cn.jimoos.rest;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.constant.PayType;
import cn.jimoos.dao.OrderMapper;
import cn.jimoos.constant.OrderStatus;
import cn.jimoos.error.OrderError;
import cn.jimoos.factory.PayFactory;
import cn.jimoos.form.PayForm;
import cn.jimoos.form.PayRefundForm;
import cn.jimoos.form.PaySearchForm;
import cn.jimoos.form.payment.PayBussForm;
import cn.jimoos.model.Order;
import cn.jimoos.payment.model.PaymentVO;
import cn.jimoos.payment.model.RefundVO;
import cn.jimoos.payment.provider.PayProvider;
import cn.jimoos.provider.impl.OfflinePayProvider;
import cn.jimoos.provider.impl.WeixinMaPayProvider;
import cn.jimoos.service.PaymentService;
import cn.jimoos.utils.mapper.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-16 22:39.
 */
@RestController
@RequestMapping("/v1/payments")
@Slf4j
public class PayApi {
    private PayFactory payFactory;
    @Resource
    OrderMapper orderMapper;
    @Resource
    PaymentService paymentService;
    @Resource
    WeixinMaPayProvider weixinMaPayProvider;
    @Resource
    OfflinePayProvider offlinePayProvider;

    @PostConstruct
    public void init() {
        payFactory = new PayFactory();
        payFactory.registryPayProvider(PayType.WECHAT_PAY.getVal(), weixinMaPayProvider);
        payFactory.registryPayProvider(PayType.OFFLINE_PAY.getVal(), offlinePayProvider);
    }

    /**
     * 支付
     *
     * @param payBusForm 支付表单
     * @throws BussException OrderError
     * @author qisheng.chen
     */
    @PostMapping(value = "/unifiedPay", produces = "application/json; charset=utf-8")
    public PaymentVO unifiedPay(@ModelAttribute PayBussForm payBusForm) throws BussException {
        log.debug("pay unifiedPay:{}", JsonMapper.defaultMapper().toJson(payBusForm));
        String orderNum = payBusForm.getOrderNum();

        Order order = orderMapper.findOneByOrderNumAndUserId(orderNum, payBusForm.getUserId());

        if (order == null) {
            throw new BussException(OrderError.ORDER_NOT_FOUND);
        }
        if (order.getStatus() >= OrderStatus.PAID && order.getStatus() != OrderStatus.CANCEL) {
            throw new BussException(OrderError.ORDER_IS_PAID);
        } else if (order.getStatus() == OrderStatus.CANCEL) {
            throw new BussException(OrderError.ORDER_IS_CANCELED);
        }
        String subject = order.getSubject();
        Integer payType = payBusForm.getPayType();

        //传递用userId
        Map<String, Object> extras = new HashMap<>(16);
        extras.put(PayProvider.USER_ID, order.getUserId());
        extras.put("content", payBusForm.getContent());
        extras.put("picture", payBusForm.getPicture());
        extras.put("orderId", order.getId());
        extras.put("openId", payBusForm.getOpenId());
        PayForm payForm = new PayForm(orderNum, payType, subject, subject, order.getRealPay(), extras);

        return paymentService.pay(payForm, payFactory.getPayProvider(payType));
    }
}
