package cn.jimoos.config;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaymentProperties {
    public final static String KEY = "payment";
    private List<Config> configs;

    @Data
    public static class Config {
        private String name;
        private String keyword;
        private Boolean active;
    }

    public static PaymentProperties defaultProperties() {
        Config config = new Config();
        config.setName("线下支付");
        config.setKeyword("offline.pay");
        config.setActive(true);
        PaymentProperties paymentProperties = new PaymentProperties();
        List<Config> configs = new ArrayList<>();
        configs.add(config);
        paymentProperties.setConfigs(configs);
        return paymentProperties;
    }
}
