package cn.jimoos.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 支付表
 *
 * @author :keepcleargas
 * @date :2021-04-20 09:56.
 */
@Data
public class Payment {
    private Long id;

    /**
     * 商户网站唯一订单号
     */
    private String outTradeNo;

    /**
     * 支付标题
     */
    private String subject;

    /**
     * 支付内容
     */
    private String body;

    /**
     * 实付 单位：元
     */
    private BigDecimal pay;

    /**
     * 0 钱包支付 1 转账 2支付宝支付 3 微信支付
     */
    private Integer payType;

    /**
     * 交易流水号 支付宝 微信 等
     */
    private String tradeNo;

    /**
     * 0 未支付 1 已支付
     */
    private Boolean paid;

    /**
     * 支付方式 如招商银行
     */
    private String paidChannel;

    /**
     * 支付时间
     */
    private Long payAt;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 0 未删除 1 已删除
     */
    private Boolean deleted;
}
