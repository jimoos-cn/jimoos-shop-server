package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractUserPageForm4L;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * 用户优惠券查询
 *
 * @author :keepcleargas
 * @date :2021-03-27 18:57.
 */
public class UserCouponQueryForm extends AbstractUserPageForm4L {
    /**
     * 优惠券状态  -1 所有  0 有效  1 已使用 | 过期了
     */
    private Integer status = -1;


    public Map<String, Object> toQueryMap() {
        Map<String, Object> qm = Maps.newHashMapWithExpectedSize(5);
        qm.put("userId", getUserId());
        qm.put("offset", offset);
        qm.put("limit", limit);
        qm.put("status", status);
        qm.put("now", System.currentTimeMillis());
        return qm;
    }
}
