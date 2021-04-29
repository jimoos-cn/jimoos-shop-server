package cn.jimoos.service;

/**
 * @author :keepcleargas
 * @date :2021-04-29 10:57.
 */
public interface PayCallbackService {
    /**
     * 微信回调
     *
     * @param outTradeNo the out trade no
     * @param extras     the extras
     * @param tradeNo    the trade no
     * @param channel    the channel
     */
    void wxPayCallback(String outTradeNo, String extras, String tradeNo, String channel);
}
