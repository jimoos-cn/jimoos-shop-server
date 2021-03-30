package cn.jimoos.form.product;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-03-30 20:32.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeProductQueryForm extends AbstractAdminPageForm4L {
    private String name;
    private Byte status;
    private Long categoryId;
    private Integer type = 0;
    private Long startTime;
    private Long endTime;

    public Map<String, Object> toQueryMap() {
        Map<String, Object> qm = Maps.newHashMapWithExpectedSize(4);
        qm.put("offset", offset);
        qm.put("limit", limit);
        qm.put("startTime", startTime);
        qm.put("endTime", endTime);
        qm.put("status", status);
        qm.put("type", type);
        qm.put("name", name);
        qm.put("categoryId", categoryId);
        return qm;
    }
}
