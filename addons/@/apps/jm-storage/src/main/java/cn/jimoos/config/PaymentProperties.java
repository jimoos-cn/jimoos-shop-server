package cn.jimoos.config;

import lombok.Data;

@Data
public class PaymentProperties {
    public final static String KEY = "payment";
    private Boolean offlinePay;
    private Boolean wxPay;
}
