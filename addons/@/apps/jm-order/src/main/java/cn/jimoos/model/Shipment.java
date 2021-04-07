package cn.jimoos.model;

import lombok.Data;

/**
 * 订单配送单
 *
 * @author keepcleargas
 * @version 1.0 Created in 2021/4/7 11:35
 */

@Data
public class Shipment {
    private Long id;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人电话
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
     * 县
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
     * 发货备注
     */
    private String comment;

    /**
     * 快递单号
     */
    private String shipCode;

    /**
     * 快递名称
     */
    private String express;

    /**
     * 快递编码
     */
    private String expressCode;

    /**
     * 发货状态
     */
    private Integer status;

    /**
     * 类型
     */
    private Integer type;

    /**
     * userId
     */
    private Long userId;

    /**
     * 外部 编号
     */
    private String outTradeNo;

    private Long createAt;

    private Long updateAt;

    private Boolean deleted;
}
