package cn.jimoos.rest;

import cn.jimoos.common.exception.BussException;
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

    @PostConstruct
    public void init() {
        payFactory = new PayFactory();
        payFactory.registryPayProvider(3, weixinMaPayProvider);
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
        extras.put(WeixinMaPayProvider.OPEN_ID, payBusForm.getOpenId());
        PayForm payForm = new PayForm(orderNum, payType, subject, subject, order.getRealPay(), extras);

        return paymentService.pay(payForm, payFactory.getPayProvider(payType));
    }

    /**
     * 主动查询 第三方接口订单状态
     * @param paySearchForm
     * @throws BussException
     */
    @GetMapping(value = "/orderQuery", produces = "application/json; charset=utf-8")
    public boolean orderQuery(@ModelAttribute PaySearchForm paySearchForm) throws BussException {
        log.debug("pay orderQuery:{}", JsonMapper.defaultMapper().toJson(paySearchForm));
        String orderNum = paySearchForm.getOrderNum();
        Order order = orderMapper.findOneByOrderNumAndUserId(orderNum, paySearchForm.getUserId());
        // 订单本地判断
        if (order == null) {
            throw new BussException(OrderError.ORDER_NOT_FOUND);
        }
        if (order.getStatus() >= OrderStatus.PAID && order.getStatus() != OrderStatus.CANCEL) {
            throw new BussException(OrderError.ORDER_IS_PAID);
        } else if (order.getStatus() == OrderStatus.CANCEL) {
            throw new BussException(OrderError.ORDER_IS_CANCELED);
        }
        // 如果第三方平台出现延迟，则调用第三方主动查询接口
        paySearchForm.setOpenId(String.valueOf(order.getUserId()));
        return paymentService.query(paySearchForm, payFactory.getPayProvider(paySearchForm.getPayType()));
    }

    /**
     * 退款
     * @param payRefundForm
     * @return
     * @throws BussException
     */
    @PostMapping(value = "/refund", produces = "application/json; charset=utf-8")
    public RefundVO refund(@ModelAttribute PayRefundForm payRefundForm) throws BussException {
        log.debug("pay orderRefund:{}", JsonMapper.defaultMapper().toJson(payRefundForm));
        String orderNum = payRefundForm.getOrderNum();
        Order order = orderMapper.findOneByOrderNum(orderNum);
        // 判断订单是否存在以及状态
        if (order == null) {
            throw new BussException(OrderError.ORDER_NOT_FOUND);
        }
        payRefundForm.setMoney(order.getRealPay());
        if (payRefundForm.getMoney().compareTo(payRefundForm.getRefundMoney()) < 0) {
            throw new BussException(OrderError.ORDER_REFUND_NOT_ENOUGH);
        }
        // todo 判断订单是否为退款状态，未商定退款状态码
        return paymentService.refund(payRefundForm, payFactory.getPayProvider(payRefundForm.getPayType()));
    }
}
