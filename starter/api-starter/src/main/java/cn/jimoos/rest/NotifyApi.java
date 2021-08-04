package cn.jimoos.rest;

import cn.jimoos.service.PayCallbackService;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author :keepcleargas
 * @date :2021-04-29 10:29.
 */
@Slf4j
@RestController
@RequestMapping("/v1/notify")
public class NotifyApi {
    public static final String WX_SUCCESS = "SUCCESS";
    @Resource
    WxPayService wxPayService;
    @Resource
    PayCallbackService payCallbackService;

    /**
     * 微信回调
     *
     * @param xmlData
     * @return
     */
    @PostMapping("/wxPay")
    public String parseOrderNotifyResult(@RequestBody String xmlData) throws WxPayException {
        final WxPayOrderNotifyResult notifyResult = this.wxPayService.parseOrderNotifyResult(xmlData);
        if (WX_SUCCESS.equals(notifyResult.getResultCode())) {
            payCallbackService.wxPayCallback(notifyResult.getOutTradeNo(), "", notifyResult.getTransactionId(), notifyResult.getBankType());
            log.info("微信支付成功" + notifyResult.toString());
            return WxPayNotifyResponse.success("成功");
        } else {
            log.info("微信支付失败:" + notifyResult.toString());
            return WxPayNotifyResponse.fail("失败");
        }
    }

    /**
     * 微信回调测试
     *
     * @param xmlData
     * @return
     */
    @PostMapping("/wxPay/test")
    public String parseOrderNotifyResultTest(@RequestBody String xmlData) throws WxPayException {
        WxPayOrderNotifyResult notifyResult = WxPayOrderNotifyResult.fromXML(xmlData);
        if (WX_SUCCESS.equals(notifyResult.getResultCode())) {
            payCallbackService.wxPayCallback(notifyResult.getOutTradeNo(), "", notifyResult.getTransactionId(), notifyResult.getBankType());
            log.info("微信支付成功" + notifyResult.toString());
            return WxPayNotifyResponse.success("成功");
        } else {
            log.info("微信支付失败:" + notifyResult.toString());
            return WxPayNotifyResponse.fail("失败");
        }
    }
}
