package cn.jimoos.form.be;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 领取记录查询
 *
 * @author :keepcleargas
 * @date :2021-03-27 21:49.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeCouponRecordQueryForm extends AbstractAdminPageForm4L {
    private Long couponId;
    private Long startTime;
    private Long endTime;
    private Boolean status;

    public Map<String, Object> toQueryMap() {
        Map<String, Object> qm = Maps.newHashMapWithExpectedSize(6);
        qm.put("offset", offset);
        qm.put("limit", limit);
        qm.put("status", status);
        qm.put("startTime", startTime);
        qm.put("endTime", endTime);
        qm.put("couponId", couponId);

        return qm;
    }
}
