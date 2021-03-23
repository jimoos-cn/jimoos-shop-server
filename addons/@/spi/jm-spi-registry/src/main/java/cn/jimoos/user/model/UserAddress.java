package cn.jimoos.user.model;

import lombok.Data;

/**
 * @author :keepcleargas
 * @date :2021-03-23 11:43.
 */

@Data
public class UserAddress {
    /**
     * id
     */
    private Long id;

    /**
     * 收件人名
     */
    private String name;

    /**
     * 收件人手机号
     */
    private String phone;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮编
     */
    private String zipCode;

    /**
     * 0 不默认 1默认
     */
    private Boolean defaultIn;

    /**
     * 0 家 1 学校 2公司
     */
    private String tag;

    /**
     * 用户 id
     */
    private Long userId;

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