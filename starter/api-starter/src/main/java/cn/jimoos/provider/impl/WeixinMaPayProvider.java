package cn.jimoos.provider.impl;

import cn.jimoos.payment.provider.PayProvider;
import cn.jimoos.utils.id.JmIdGenerator;
import cn.jimoos.utils.mapper.JsonMapper;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayOrderQueryRequest;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-20 20:35.
 */
@Component
public class WeixinMaPayProvider implements PayProvider {
    @Resource
    WxPayService wxPayService;

    @Resource
    JmIdGenerator idGenerator;

    public static final String OPEN_ID = "openId";
    public static final String WX_SUCCESS = "SUCCESS";

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
        // FIXME 重复调用 OUT_TRADE_NO_USED	商户订单号重复
        //WxPayUnifiedOrderResult wxPayUnifiedOrderResult = wxPayService.unifiedOrder(wxPayUnifiedOrderRequest);
        WxPayMpOrderResult wxPayMpOrderResult = wxPayService.createOrder(wxPayUnifiedOrderRequest);

        return JsonMapper.defaultMapper().toJson(wxPayMpOrderResult);
    }


    @Override
    public boolean queryByOrder(String outTradeNo) {
        WxPayOrderQueryRequest wxPayOrderQueryRequest = new WxPayOrderQueryRequest();
        wxPayOrderQueryRequest.setOutTradeNo(outTradeNo);
        try {
            WxPayOrderQueryResult wxPayOrderQueryResult = wxPayService.queryOrder(wxPayOrderQueryRequest);
            return WX_SUCCESS.equals(wxPayOrderQueryResult.getReturnCode())
                    && WX_SUCCESS.equals(wxPayOrderQueryResult.getResultCode())
                    && WX_SUCCESS.equals(wxPayOrderQueryResult.getTradeState());
        } catch (WxPayException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean refund(String outTradeNo, BigDecimal money, BigDecimal refundMoney) {
        WxPayRefundRequest wxPayRefundRequest = new WxPayRefundRequest();
        wxPayRefundRequest.setOutRefundNo(outTradeNo);
        // 单位为分，只能为整数
        refundMoney = refundMoney.multiply(new BigDecimal(100));
        money = money.multiply(new BigDecimal(100));
        wxPayRefundRequest.setRefundFee(refundMoney.intValue());
        wxPayRefundRequest.setTotalFee(money.intValue());
        // 生成退款唯一标识符
        wxPayRefundRequest.setOutRefundNo(idGenerator.getId());
        // 不启用通知
        wxPayRefundRequest.setNotifyUrl(null);
        // todo 退款数据表待生成
        try {
            WxPayRefundResult result = wxPayService.refund(wxPayRefundRequest);
            if (WX_SUCCESS.equals(result.getResultCode())) {
                return true;
            }
        } catch (WxPayException e) {
            e.printStackTrace();
        }
        return false;
    }
}
