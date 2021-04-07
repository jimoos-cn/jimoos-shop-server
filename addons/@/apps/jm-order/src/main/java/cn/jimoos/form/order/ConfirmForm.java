package cn.jimoos.form.order;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :keepcleargas
 * @date :2020-05-21 16:47.
 */
@NoArgsConstructor
@Data
public class ConfirmForm {
    private Long userId;
    private Long orderId;

    public ConfirmForm(Long userId, Long orderId) {
        this.userId = userId;
        this.orderId = orderId;
    }
}
