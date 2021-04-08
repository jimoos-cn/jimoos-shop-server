package cn.jimoos.dto;

import cn.jimoos.order.dic.FeeType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-08 15:25.
 */
@Data
public class FeeItemDTO {
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
     * 类型 邮费等
     */
    private FeeType feeType;
    /**
     * 优惠ID
     */
    private Long feeId;

    /**
     * 优惠价格
     */
    private BigDecimal feePrice;

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

    public FeeItemDTO(Long productId, Long skuId, Integer number, FeeType feeType, Long feeId, BigDecimal feePrice) {
        this.productId = productId;
        this.skuId = skuId;
        this.number = number;
        this.feeType = feeType;
        this.feeId = feeId;
        this.feePrice = feePrice;
    }
}
