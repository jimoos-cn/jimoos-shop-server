package cn.jimoos.form.order;


import cn.jimoos.order.dic.DiscountType;
import cn.jimoos.order.dic.FeeType;
import cn.jimoos.product.model.ProductItem;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单表单
 *
 * @author :keepcleargas
 * @date :2021-04-08 15:53.
 */
@Data
public class OrderForm {
    /**
     * 订单类型：一般分 实体/虚拟等
     */
    @NotBlank
    private String type;
    /**
     * 用户ID
     */
    @NotNull
    private Long userId;
    /**
     * addressId
     */
    private Long addressId;
    /**
     * 留言
     */
    private String comment;
    /**
     * 子订单创建信息
     */
    @NotEmpty
    @Valid
    private List<OrderItemForm> orderItems;

    /**
     * 优惠配置
     */
    private List<ItemDiscount> itemDiscounts;
    /**
     * 费用配置
     */
    private List<ItemFee> itemFees;
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

    public BigDecimal getTotalProductPrice() {
        if (CollectionUtils.isEmpty(orderItems)) {
            return BigDecimal.valueOf(0);
        } else {
            return orderItems.stream().map(orderItem -> orderItem.getProductPrice().multiply(BigDecimal.valueOf(orderItem.getNumber()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    public void addOrderItemForm(OrderItemForm orderItemForm) {
        if (CollectionUtils.isEmpty(orderItems)) {
            orderItems = new ArrayList<>();
        }
        orderItems.add(orderItemForm);
    }

    @Data
    @NoArgsConstructor
    public static class ItemDiscount {
        private DiscountType discountType;
        private Long discountId = 0L;
        /**
         * 折扣
         */
        private BigDecimal discountPrice;
        private String desc;
        /**
         * 商品 ID 必填
         */
        private Long productId;
        /**
         * Sku ID 可选
         */
        private Long skuId;

        /**
         * 额外信息 Map
         */
        private Map<String, Object> extraMap = new HashMap<>();

        public ItemDiscount(ProductItem productItem, DiscountType type, long discountId, BigDecimal margin) {
            this.productId = productItem.getProductId();
            this.skuId = productItem.getSkuId();
            this.discountType = type;
            this.desc = type.getDes();
            this.discountId = discountId;
            this.discountPrice = margin;
        }
    }

    @Data
    public static class ItemFee {
        private FeeType feeType;
        private Long feeId = 0L;
        private BigDecimal fee;
        private String desc;
        /**
         * 商品 ID 必填
         */
        private Long productId;
        /**
         * Sku ID 可选
         */
        private Long skuId;

        /**
         * 额外信息 Map
         */
        private Map<String, Object> extraMap = new HashMap<>();

    }
}
