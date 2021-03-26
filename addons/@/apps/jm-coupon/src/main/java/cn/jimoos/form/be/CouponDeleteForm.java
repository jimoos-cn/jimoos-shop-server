package cn.jimoos.form.be;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;

/**
 * 优惠券 删除表单
 *
 * @author :keepcleargas
 * @date :2021-03-26 22:37.
 */
@Data
public class CouponDeleteForm extends AbstractAdminForm4L {
    private Long couponId;
}
