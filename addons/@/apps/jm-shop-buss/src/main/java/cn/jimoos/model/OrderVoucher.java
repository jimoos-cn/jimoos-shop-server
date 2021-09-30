package cn.jimoos.model;

import java.math.BigDecimal;

import cn.jimoos.constant.OrderVoucherStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单支付凭证
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVoucher {
    private Long id;

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 状态 0审核中 1通过 -1审核失败
     */
    private Byte status;

    /**
     * 凭证内容
     */
    private String content;

    /**
     * 凭证图片
     */
    private String picture;

    /**
     * 失败原因
     */
    private String reason;

    /**
     * 应付金额
     */
    private BigDecimal money;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 删除标志
     */
    private Boolean deleted;

    /**
     * 新建
     */
    public OrderVoucher(String content,String picture, Long orderId, BigDecimal momey) {
        this.content = content;
        this.picture = picture;
        this.orderId = orderId;
        this.money = momey;
        this.status = OrderVoucherStatus.ONGOING;
        this.createAt = System.currentTimeMillis();
        this.updateAt = System.currentTimeMillis();
        this.deleted = false;
    }
}
