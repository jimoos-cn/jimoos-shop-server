package cn.jimoos.form.be;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 优惠券上下架表单
 *
 * @author :keepcleargas
 * @date :2021-03-26 22:15.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeCouponStatusForm extends AbstractAdminForm4L {
    private Long couponId;
    private Boolean status;
}
