package cn.jimoos.provider.impl;

import cn.jimoos.payment.provider.PayProvider;
import cn.jimoos.utils.mapper.JsonMapper;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.service.WxPayService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-20 20:35.
 */
public class WeixinMaPayProvider implements PayProvider {
    @Resource
    WxPayService wxPayService;

    public static final String OPEN_ID = "openId";

    @Override
    public String pay(String outTradeNo, String subject, String body, BigDecimal price) throws Exception {
        throw new UnsupportedOperationException();
    }

    /**
     * extras 中请传入 openId
     */
    @Override
    public String pay(String outTradeNo, String subject, String body, BigDecimal price, Map<String, Object> extras) throws Exception {
        String openId = String.valueOf(extras.get(OPEN_ID));
        WxPayUnifiedOrderRequest wxPayUnifiedOrderRequest = new WxPayUnifiedOrderRequest();
        wxPayUnifiedOrderRequest.setBody(body);
        wxPayUnifiedOrderRequest.setOutTradeNo(outTradeNo);
        wxPayUnifiedOrderRequest.setTotalFee(BaseWxPayRequest.yuanToFen(price.toPlainString()));
        wxPayUnifiedOrderRequest.setSpbillCreateIp("127.0.0.1");
        wxPayUnifiedOrderRequest.setTradeType("JSAPI");
        wxPayUnifiedOrderRequest.setSignType("MD5");
        extras.remove(OPEN_ID);
        wxPayUnifiedOrderRequest.setAttach(JsonMapper.defaultMapper().toJson(extras));
        wxPayUnifiedOrderRequest.setOpenid(openId);

        WxPayUnifiedOrderResult wxPayUnifiedOrderResult = wxPayService.unifiedOrder(wxPayUnifiedOrderRequest);

        WxPayMpOrderResult wxPayMpOrderResult = wxPayService.createOrder(wxPayUnifiedOrderRequest);

        return JsonMapper.defaultMapper().toJson(wxPayMpOrderResult);

    }
}
