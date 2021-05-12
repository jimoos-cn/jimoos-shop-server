package cn.jimoos.vo;

import cn.jimoos.form.order.OrderForm;
import cn.jimoos.form.order.OrderItemForm;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-04-13 11:12.
 */
@Data
public class AbstractOrderPre implements IOrderPre {
    private Long userId;
    private List<OrderItemForm> orderItems = new ArrayList<>();
    private List<OrderForm.ItemDiscount> discountItems = new ArrayList<>();

    public void addOrderItem(OrderItemForm orderItem) {
        orderItems.add(orderItem);
    }

    public void addOrderItems(List<OrderItemForm> orderItems) {
        this.orderItems.addAll(orderItems);
    }

    public void addDiscountItems(List<OrderForm.ItemDiscount> items) {
        discountItems.addAll(items);
    }

    public void addDiscountItem(OrderForm.ItemDiscount item) {
        discountItems.add(item);
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public BigDecimal getTotalDiscount() {
        if (CollectionUtils.isEmpty(discountItems)) {
            return BigDecimal.valueOf(0);
        } else {
            return discountItems.stream().map(OrderForm.ItemDiscount::getDiscountPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    @Override
    public BigDecimal getTotalPrice() {
        if (CollectionUtils.isEmpty(orderItems)) {
            return BigDecimal.valueOf(0);
        } else {
            return orderItems.stream().map(orderItem -> orderItem.getProductPrice().multiply(BigDecimal.valueOf(orderItem.getNumber()))).reduce(BigDecimal.ZERO, BigDecimal::add);
        }
    }

    /**
     * 按商品的维度 返回实际支付
     *
     * @param productId productId
     * @return BigDecimal
     */
    public BigDecimal computeRealPayByProductId(Long productId) {
        if (CollectionUtils.isEmpty(orderItems)) {
            return BigDecimal.valueOf(0);
        } else {
            BigDecimal totalPrice = orderItems.stream().filter(orderItem -> orderItem.getProductId().equals(productId)).map(orderItem -> orderItem.getProductPrice().multiply(BigDecimal.valueOf(orderItem.getNumber()))).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal totalDiscount = BigDecimal.ZERO;
            if (!CollectionUtils.isEmpty(discountItems)) {
                totalDiscount = discountItems.stream().filter(orderItem -> orderItem.getProductId().equals(productId)).map(OrderForm.ItemDiscount::getDiscountPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            return totalPrice.subtract(totalDiscount);
        }
    }

    /**
     * 按商品 SkuId的维度 返回实际支付
     *
     * @param skuId sku Id
     * @return BigDecimal
     */
    public BigDecimal computeRealPayBySkuId(Long skuId) {
        if (CollectionUtils.isEmpty(orderItems)) {
            return BigDecimal.valueOf(0);
        } else {
            BigDecimal totalPrice = orderItems.stream().filter(orderItem -> orderItem.getSkuId().equals(skuId)).map(orderItem -> orderItem.getProductPrice().multiply(BigDecimal.valueOf(orderItem.getNumber()))).reduce(BigDecimal.ZERO, BigDecimal::add);
            BigDecimal totalDiscount = BigDecimal.ZERO;
            if (!CollectionUtils.isEmpty(discountItems)) {
                totalDiscount = discountItems.stream().filter(orderItem -> orderItem.getSkuId().equals(skuId)).map(OrderForm.ItemDiscount::getDiscountPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            }
            return totalPrice.subtract(totalDiscount);
        }
    }

    @Override
    public BigDecimal getRealPay() {
        BigDecimal totalPrice = getTotalPrice();
        if (BigDecimal.ZERO.compareTo(totalPrice) <= 0) {
            return totalPrice.subtract(getTotalDiscount());
        } else {
            return BigDecimal.ZERO;
        }
    }
}
