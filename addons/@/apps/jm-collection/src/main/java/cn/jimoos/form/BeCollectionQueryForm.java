package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-29 14:51.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeCollectionQueryForm extends AbstractAdminPageForm4L {
    private String name;
    private Boolean status;

    public Map<String, Object> toQueryMap() {
        Map<String, Object> qm = Maps.newHashMapWithExpectedSize(4);
        qm.put("offset", offset);
        qm.put("limit", limit);
        qm.put("status", status);
        qm.put("name", name);
        return qm;
    }
}
