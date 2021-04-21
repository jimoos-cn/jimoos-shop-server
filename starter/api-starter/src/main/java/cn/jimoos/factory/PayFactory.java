package cn.jimoos.factory;


import cn.jimoos.common.exception.BussException;
import cn.jimoos.error.PayError;
import cn.jimoos.payment.provider.PayProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * 支付方式
 *
 * @author :keepcleargas
 * @date :2021-04-20 20:37.
 */
public class PayFactory {
    private Map<Integer, PayProvider> payProviderMap = new HashMap<>();

    /**
     * 注册可用支付方式
     *
     * @param type
     * @param payProvider
     */
    public void registryPayProvider(Integer type, PayProvider payProvider) {
        payProviderMap.put(type, payProvider);
    }

    /**
     * 取消注册可用支付方式
     *
     * @param type
     */
    public void unRegistryPayProvider(Integer type) {
        payProviderMap.put(type, null);
    }

    public PayProvider getPayProvider(Integer type) throws BussException {
        PayProvider payProvider = payProviderMap.get(type);
        if (payProvider == null) {
            throw new BussException(PayError.PAY_TYPE_NOT_SUPPORT);
        } else {
            return payProvider;
        }
    }
}
