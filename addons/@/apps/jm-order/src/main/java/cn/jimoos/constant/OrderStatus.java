package cn.jimoos.constant;


/**
 * @author :keepcleargas
 * @date :2021-04-07 14:04.
 */
public class  OrderStatus {
    private OrderStatus() {

    }

    /**
     * 新订单
     */
    public static final byte ORDER_NEW = 0;
    /**
     * 已支付
     */
    public static final byte PAID = 1;
    /**
     * 已发货
     */
    public static final byte DELIVERY = 2;
    /**
     * 已签收
     */
    public static final byte RECEIVE = 3;
    /**
     * 已评价
     */
    public static final byte RATED = 4;


    /**
     * 退款中
     */
    public static final byte REFUND = -1;

    /**
     * 退款完成
     */
    public static final byte REFUND_DONE = -2;

    /**
     * 退款取消
     */
    public static final byte REFUND_CANCEL= -3;

    /**
     * 已取消
     */
    public static final byte CANCEL = -99;
}
