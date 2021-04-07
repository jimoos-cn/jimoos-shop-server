package cn.jimoos.form.order;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author keepcleargas
 * @version 1.0 Created in 2021/1/26 13:28
 */
@NoArgsConstructor
@Data
public class RemindDeliveryForm {
    private Long userId;
    private Long orderId;

    public RemindDeliveryForm(Long userId, Long orderId) {
        this.userId = userId;
        this.orderId = orderId;
    }
}
