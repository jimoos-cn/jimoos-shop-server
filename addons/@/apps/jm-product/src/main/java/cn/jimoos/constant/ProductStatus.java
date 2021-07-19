package cn.jimoos.constant;

import lombok.Data;

/**
 * @author SiletFlower
 * @date 2021/7/19 14:46:01
 * @description 商品状态常量
 */
@Data
public class ProductStatus {

    private ProductStatus(){}

    /**
     * 未上架
     */
    public static final byte UN_PUSH = 0;

    /**
     * 待审核
     */
    public static final byte UN_CHECK = 1;

    /**
     * 上架中
     */
    public static final byte PUSH = 2;

    /**
     * 未通过
     */
    public static final byte UN_PASS = 3;
}
