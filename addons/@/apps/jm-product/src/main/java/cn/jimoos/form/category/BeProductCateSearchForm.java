package cn.jimoos.form.category;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author keepcleargas
 * @date 2021-03-29 19:52.
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BeProductCateSearchForm extends AbstractAdminPageForm4L {
    /**
     * 分类名字
     */
    private String name;
    /**
     * 父分类节点 ID
     */
    private Long pid;

    public Map<String, Object> toQueryMap() {
        Map<String, Object> qm = Maps.newHashMapWithExpectedSize(4);
        qm.put("offset", offset);
        qm.put("limit", limit);
        qm.put("pid", pid);
        qm.put("name", name);
        return qm;
    }
}
