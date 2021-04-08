package cn.jimoos.dto;

import cn.jimoos.order.dic.DiscountType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-08 14:59.
 */
@Data
public class DiscountItemDTO {
    /**
     * 商品Id
     */
    private Long productId;
    /**
     * skuId
     */
    private Long skuId;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 优惠类型
     */
    private DiscountType discountType;
    /**
     * 优惠ID
     */
    private Long discountId;

    /**
     * 优惠价格
     */
    private BigDecimal discountPrice;

    /**
     * 额外信息 Map
     */
    private Map<String, Object> extraMap = new HashMap<>();

    public void addExtraMap(String key, Object value) {
        this.extraMap.put(key, value);
    }

    public void addAllExtraMap(Map<String, Object> extraMap) {
        this.extraMap.putAll(extraMap);
    }

    public DiscountItemDTO(Long productId, Long skuId, Integer number, DiscountType discountType, Long discountId, BigDecimal discountPrice) {
        this.productId = productId;
        this.skuId = skuId;
        this.number = number;
        this.discountType = discountType;
        this.discountId = discountId;
        this.discountPrice = discountPrice;
    }
}
