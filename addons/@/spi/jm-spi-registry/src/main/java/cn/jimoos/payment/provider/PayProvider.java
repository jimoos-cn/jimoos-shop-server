package cn.jimoos.payment.provider;

import java.math.BigDecimal;
import java.util.Map;

/**
 * The interface Pay provider.
 *
 * @author :keepcleargas
 * @date :2020-12-17 10:07.
 */
public interface PayProvider {
    public static final String USER_ID = "userId";

    /**
     * 支付
     *
     * @param outTradeNo the out trade no
     * @param subject    the subject
     * @param body       the body
     * @param price      the price
     * @return string
     * @throws Exception the exception
     */
    String pay(String outTradeNo, String subject, String body, BigDecimal price) throws Exception;

    /**
     * 支付
     *
     * @param outTradeNo the out trade no
     * @param subject    the subject
     * @param body       the body
     * @param price      the price
     * @param extras     the extras
     * @return string
     * @throws Exception the exception
     */
    String pay(String outTradeNo, String subject, String body, BigDecimal price, Map<String, Object> extras) throws Exception;
}
