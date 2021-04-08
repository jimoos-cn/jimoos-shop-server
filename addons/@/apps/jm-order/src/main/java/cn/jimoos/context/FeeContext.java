package cn.jimoos.context;

import cn.jimoos.form.order.OrderForm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @author :keepcleargas
 * @date :2021-04-08 15:42.
 */
@Data
@NoArgsConstructor
public class FeeContext {
    /**
     * 优惠信息
     */
    private List<OrderForm.ItemFee> feeItems = Lists.newArrayList();

    public FeeContext(List<OrderForm.ItemFee> feeItems) {
        this.feeItems = feeItems;
    }

    /**
     * 总的的收费信息
     */
    @JsonIgnore
    public BigDecimal getTotalFee() {
        if (CollectionUtils.isEmpty(feeItems)) {
            return BigDecimal.ZERO;
        }
        return feeItems.parallelStream().map(OrderForm.ItemFee::getFee).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * 获取过滤过的优惠信息 by productId and skuId
     */
    @JsonIgnore
    public BigDecimal getFilterFee(Long productId, Long skuId) {
        if (CollectionUtils.isEmpty(feeItems)) {
            return BigDecimal.ZERO;
        }
        return feeItems.parallelStream().filter(r -> Objects.equals(productId, r.getProductId()) && Objects.equals(skuId, r.getSkuId())).map(OrderForm.ItemFee::getFee).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
