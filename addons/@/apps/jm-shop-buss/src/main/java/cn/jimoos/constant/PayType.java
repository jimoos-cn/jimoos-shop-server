package cn.jimoos.constant;

/**
 * @author SiletFlower
 * @date 2021/9/29 09:26:15
 * @description
 */
public enum PayType {
    /**
     * 线下 转账支付
     */
    OFFLINE_PAY(1),
    /**
     * 3 微信支付
     */
    WECHAT_PAY(3);
    
    PayType(int val) {
        this.val = val;
    }

    private int val;

    public int getVal() {
        return val;
    }
}
