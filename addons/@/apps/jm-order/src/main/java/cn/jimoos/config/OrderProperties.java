package cn.jimoos.config;

import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-04-15 15:13.
 */
@Data
public class OrderProperties {
    public static final String KEY = "order.config";
    /**
     * 订单过期取消时间
     * 一般默认 45 分钟,秒 为单位
     */
    private Integer orderExpiredTime = 45 * 60;
    /**
     * 默认发货后 自动确认时间
     * 默认 14天 ，秒为单位
     */
    private Integer autoConfirmTime = 14 * 24 * 3600;
}
