package cn.jimoos.form.order.be;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-16 13:30.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeOrderQueryForm extends AbstractAdminPageForm4L {
    private Long startTime;
    private Long endTime;
    private Byte status = -1;
    private String phone;
    private String orderNum;
    private String orderType;
    private String userId;

    public Map<String, Object> toQueryMap() {
        Map<String, Object> qm = Maps.newHashMapWithExpectedSize(8);
        qm.put("offset", offset);
        qm.put("limit", limit);
        qm.put("status", status);
        qm.put("orderNum", orderNum);
        qm.put("startTime", startTime);
        qm.put("endTime", endTime);
        qm.put("orderType", orderType);
        qm.put("userId", userId);
        return qm;
    }
}
