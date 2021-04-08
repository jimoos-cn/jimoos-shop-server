package cn.jimoos.context;

import cn.jimoos.form.order.OrderForm;
import cn.jimoos.order.dic.DiscountType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author :keepcleargas
 * @date :2021-04-08 14:48.
 */
@NoArgsConstructor
@Data
public class DiscountContext {
    /**
     * 优惠信息
     */
    private List<OrderForm.ItemDiscount> discountItems = Lists.newArrayList();

    public DiscountContext(List<OrderForm.ItemDiscount> discountItems) {
        this.discountItems = discountItems;
    }

    /**
     * 总的的优惠信息
     */
    @JsonIgnore
    public BigDecimal getTotalDiscount() {
        if (CollectionUtils.isEmpty(discountItems)) {
            return BigDecimal.ZERO;
        }
        return discountItems.parallelStream().map(OrderForm.ItemDiscount::getDiscountPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 获取过滤过的优惠信息 by productId
     */
    @JsonIgnore
    public BigDecimal getFilterDiscount(Long productId) {
        if (CollectionUtils.isEmpty(discountItems)) {
            return BigDecimal.ZERO;
        }
        return discountItems.parallelStream().filter(r -> Objects.equals(productId, r.getProductId())).map(OrderForm.ItemDiscount::getDiscountPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @JsonIgnore
    public List<OrderForm.ItemDiscount> getFilterDiscountList(Long productId) {
        if (CollectionUtils.isEmpty(discountItems)) {
            return Lists.newArrayList();
        }
        return discountItems.parallelStream().filter(r -> Objects.equals(productId, r.getProductId())).collect(Collectors.toList());
    }

    /**
     * 获取过滤过的优惠信息 by productId and skuId
     */
    @JsonIgnore
    public BigDecimal getFilterDiscount(Long productId, Long skuId) {
        if (CollectionUtils.isEmpty(discountItems)) {
            return BigDecimal.ZERO;
        }
        return discountItems.parallelStream().filter(r -> Objects.equals(productId, r.getProductId()) && Objects.equals(skuId, r.getSkuId())).map(OrderForm.ItemDiscount::getDiscountPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @JsonIgnore
    public List<OrderForm.ItemDiscount> getFilterDiscountList(Long productId, Long skuId) {
        if (CollectionUtils.isEmpty(discountItems)) {
            return Lists.newArrayList();
        }
        return discountItems.parallelStream().filter(r -> Objects.equals(productId, r.getProductId()) && Objects.equals(skuId, r.getSkuId())).collect(Collectors.toList());
    }

    /**
     * 获取过滤过的优惠信息 by discountType
     */
    @JsonIgnore
    public BigDecimal getFilterDiscount(DiscountType discountType) {
        if (CollectionUtils.isEmpty(discountItems)) {
            return BigDecimal.ZERO;
        }
        return discountItems.parallelStream().filter(r -> Objects.equals(discountType, r.getDiscountType())).map(OrderForm.ItemDiscount::getDiscountPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }


    @JsonIgnore
    public List<OrderForm.ItemDiscount> getFilterDiscountList(DiscountType discountType) {
        if (CollectionUtils.isEmpty(discountItems)) {
            return Lists.newArrayList();
        }
        return discountItems.parallelStream().filter(r -> Objects.equals(discountType, r.getDiscountType())).collect(Collectors.toList());
    }


    public Map<Long, List<OrderForm.ItemDiscount>> groupBySkuIdMap() {
        return this.discountItems.parallelStream().collect(Collectors.groupingBy(
                OrderForm.ItemDiscount::getSkuId, Collectors.toList()
        ));
    }

    public Map<Long, List<OrderForm.ItemDiscount>> groupByProductIdMap() {
        return this.discountItems.parallelStream().collect(Collectors.groupingBy(
                OrderForm.ItemDiscount::getProductId, Collectors.toList()
        ));
    }

    public Map<DiscountType, List<OrderForm.ItemDiscount>> groupByDiscountTypeMap() {
        return this.discountItems.parallelStream().collect(Collectors.groupingBy(
                OrderForm.ItemDiscount::getDiscountType, Collectors.toList()
        ));
    }
}
