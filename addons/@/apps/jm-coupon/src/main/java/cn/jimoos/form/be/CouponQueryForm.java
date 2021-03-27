package cn.jimoos.form.be;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-03-26 22:04.
 */
@Data
public class CouponQueryForm extends AbstractAdminPageForm4L {
    /**
     * 兑换码
     */
    private String code;
    /**
     * 描述查询
     */
    private String name;
    /**
     * 上下架状态
     */
    private Boolean status;

    /**
     * 有效领取 startTime
     */
    private Long startTime;
    /**
     * 有效领取 endTime
     */
    private Long endTime;

    public Map<String, Object> toQueryMap() {
        Map<String, Object> qm = Maps.newHashMapWithExpectedSize(7);
        qm.put("offset", offset);
        qm.put("limit", limit);
        qm.put("code", code);
        qm.put("name", name);
        qm.put("status", status);
        qm.put("startTime", startTime);
        qm.put("endTime", endTime);

        return qm;
    }
}
