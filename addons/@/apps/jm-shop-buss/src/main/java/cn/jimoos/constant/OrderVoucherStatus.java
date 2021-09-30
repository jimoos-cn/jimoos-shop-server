package cn.jimoos.constant;

/**
 * @author SiletFlower
 * @date 2021/9/29 14:04:04
 * @description 凭证状态
 */
public class OrderVoucherStatus {
    /**
     * 审核中
     */
    public static final Byte ONGOING = 0;

    /**
     * 已通过
     */
    public static final Byte PASS = 1;

    /**
     * 失败
     */
    public static final Byte FAIL = -1;
}
