package cn.jimoos.form;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-26 21:10.
 */
@Data
public class AdminQueryForm extends AbstractAdminPageForm4L {

    /**
     * 管理员名字关键字
     */
    private String name;

    public Map<String, Object> toQueryMap() {
        Map<String, Object> qm = Maps.newHashMapWithExpectedSize(4);
        qm.put("offset", offset);
        qm.put("limit", limit);
        qm.put("name", name);
        return qm;
    }
}
