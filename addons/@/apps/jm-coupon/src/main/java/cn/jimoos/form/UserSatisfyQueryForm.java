package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractUserPageForm4L;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-13 15:08.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserSatisfyQueryForm extends AbstractUserPageForm4L {
    /**
     * 满减条件
     */
    private BigDecimal beyond;

    public Map<String, Object> toQueryMap() {
        Map<String, Object> qm = Maps.newHashMapWithExpectedSize(5);
        qm.put("userId", getUserId());
        qm.put("offset", offset);
        qm.put("limit", limit);
        qm.put("now", System.currentTimeMillis());
        qm.put("beyond", beyond);
        return qm;
    }
}
