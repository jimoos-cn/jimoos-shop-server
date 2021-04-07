package cn.jimoos.form.order;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 取消订单
 *
 * @author :keepcleargas
 * @date :2021-04-07 10:50.
 */
@Data
@NoArgsConstructor
public class CancelForm {
    private Long userId;
    private Long orderId;

    public CancelForm(Long userId, Long orderId) {
        this.userId = userId;
        this.orderId = orderId;
    }
}
