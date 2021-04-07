package cn.jimoos.product.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-07 15:28.
 */
@Data
public class ProductItem {
    private Long productId = 0L;
    /**
     * 商品类型 存入 orderItem 表
     */
    private String productType;

    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品图片
     */
    private String productCover;
    //------------------------------ sku start 只有商品的时候使用 --------------------------------

    private Long skuId;
    /**
     * sku 的规格
     */
    private String skuName;

    /**
     * 封面
     */
    private String skuCover;
    //------------------------------ sku end 只有商品的时候使用 --------------------------------
    /**
     * 商品原价，价格不可空
     */
    private BigDecimal price;
    /**
     * 一级分销 -1 则 无设置，0-100 为 分销百分比
     */
    private Integer aRate = -1;
    /**
     * 二级分销 -1 则 无设置，0-100 为 分销百分比
     */
    private Integer bRate = -1;

    /**
     * 额外信息
     */
    private Map<String, Object> extras;

    public ProductItem() {
    }

    public ProductItem(String productType) {
        this.productType = productType;
    }


    public ProductItem(Long productId, String productName, String productCover, BigDecimal price) {
        this.productId = productId;
        this.productName = productName;
        this.productCover = productCover;
        this.price = price;
    }

    public ProductItem(Long productId, String productName, String productCover, BigDecimal price, String productType) {
        this.productId = productId;
        this.productName = productName;
        this.productCover = productCover;
        this.price = price;
        this.productType = productType;
    }
}
