package cn.jimoos.error;


import cn.jimoos.common.error.IErrorCode;

/**
 * @author :keepcleargas
 * @date :2021-04-07 12:42.
 */
public enum OrderError implements IErrorCode {
    /**
     * 订单错误相关
     */
    ORDER_NOT_FOUND("order.order_not_found", "订单找不到了"),
    ORDER_TYPE_NOT_SUPPORT("order.order_type_not_support", "订单类型不支持"),
    ORDER_STATUS_NOT_VALID("order.order_status_not_valid", "订单状态不正确,请刷新重试"),
    ADDRESS_NOT_FOUND("order.address_not_found", "配送地址未找到"),
    CART_EMPTY("order.cart_empty", "请选择需要购买的购物车商品"),
    ORDER_IS_PAID("order.order_is_paid", "订单已支付"),
    ORDER_IS_CANCELED("order.order_is_canceled", "订单已取消"),
    PRODUCT_TYPE_NOT_FOUND("order.product_type_not_found", "商品类别未找到"),
    PRODUCT_NOT_FOUND("order.product_not_found", "商品未找到"),
    SHIPMENT_NOT_EXIST("shipment.shipment_not_exist", "发货单找不到了"),
    ORDER_REFUND_NOT_ENOUGH("order.refund_not_enough", "退款金额大于订单金额"),
    ORDER_REFUND_NOT_EXIST("order.refund_not_exist", "退款订单不存在"),
    ORDER_PAYMENT_NOT_FOUND("order_payment_not_found", "线下支付凭证未找到");

    private String code;
    private String desc;

    OrderError(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDesc() {
        return desc;
    }
}
