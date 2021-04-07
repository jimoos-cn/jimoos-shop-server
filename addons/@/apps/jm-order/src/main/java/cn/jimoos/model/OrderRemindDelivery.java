package cn.jimoos.model;

import lombok.Data;

/**
 * 提醒发货
 *
 * @author :keepcleargas
 * @date :2021/4/7 11:35
 */
@Data
public class OrderRemindDelivery {
    private Long id;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 用户id
     */
    private Long uid;

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
