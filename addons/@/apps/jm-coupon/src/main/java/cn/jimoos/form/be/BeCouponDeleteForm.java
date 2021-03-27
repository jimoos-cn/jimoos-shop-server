package cn.jimoos.form.be;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 优惠券 删除表单
 *
 * @author :keepcleargas
 * @date :2021-03-26 22:37.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeCouponDeleteForm extends AbstractAdminForm4L {
    private Long couponId;
}
