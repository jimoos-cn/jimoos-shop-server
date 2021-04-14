package cn.jimoos.form.order;

import cn.jimoos.utils.form.AbstractUserPageForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户订单查询
 *
 * @author :keepcleargas
 * @date :2021-04-14 09:34.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserOrderQueryForm extends AbstractUserPageForm4L {
    private byte status;
}
