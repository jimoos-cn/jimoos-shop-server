package cn.jimoos.form.attr;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-03-30 14:24.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeProductAttrQueryForm extends AbstractAdminPageForm4L {
    private String name;

    public Map<String, Object> toQueryMap() {
        Map<String, Object> qm = Maps.newHashMapWithExpectedSize(4);
        qm.put("offset", offset);
        qm.put("limit", limit);
        qm.put("name", name);
        return qm;
    }

}
