package cn.jimoos.provider.impl;

import cn.jimoos.model.OrderVoucher;
import cn.jimoos.payment.provider.PayProvider;
import cn.jimoos.service.OrderVoucherService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author SiletFlower
 * @date 2021/9/29 09:29:25
 * @description
 */
@Component
public class OfflinePayProvider implements PayProvider {
    @Resource
    OrderVoucherService orderVoucherService;

    private static final String SUCCESS = "SUCCESS";

    @Override
    public String pay(String outTradeNo, String subject, String body, BigDecimal price) throws Exception {
        throw new UnsupportedOperationException();
    }

    @Override
    public String pay(String outTradeNo, String subject, String body, BigDecimal price, Map<String, Object> extras) throws Exception {
        // 保存凭证
        String content = (String) extras.get("content");
        String picture = (String) extras.get("picture");
        Long orderId = (Long) extras.get("orderId");
        OrderVoucher orderVoucher = new OrderVoucher(content, picture, orderId, price);
        orderVoucherService.save(orderVoucher);
        return SUCCESS;
    }

    @Override
    public boolean queryByOrder(String outTradeNo) {
        return false;
    }

    @Override
    public boolean refund(String outTradeNo, BigDecimal money, BigDecimal refundMoney) {
        return false;
    }
}
