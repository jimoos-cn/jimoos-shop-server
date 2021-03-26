package cn.jimoos.model;

import lombok.Data;

/**
 * 用户优惠券表
 *
 * @author :keepcleargas
 * @date :2021-03-23 15:35.
 */
@Data
public class CouponRecord {
    private Long id;

    /**
     * 优惠id
     */
    private Long couponId;

    /**
     * 用户
     */
    private Long userId;

    /**
     * 0未使用1已使用
     */
    private Boolean status;

    /**
     * 过期时间
     */
    private Long expired;

    /**
     * 创建时间
     */
    private Long createAt;

    /**
     * 更新时间
     */
    private Long updateAt;

    /**
     * 0 未删除 1删除
     */
    private Boolean deleted;
}