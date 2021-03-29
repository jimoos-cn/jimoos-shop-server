package cn.jimoos.form.tag;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author keepcleargas
 * @date 2021-03-29 20:52.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BeProductTagSearchForm extends AbstractAdminPageForm4L {
    private String name;
    private Integer type;

    public Map<String, Object> toQueryMap() {
        Map<String, Object> qm = Maps.newHashMapWithExpectedSize(4);
        qm.put("offset", offset);
        qm.put("limit", limit);
        qm.put("type", type);
        qm.put("name", name);
        return qm;
    }
}
