package cn.jimoos.form.product;

import cn.jimoos.entity.ProductEntity;
import cn.jimoos.utils.form.AbstractUserPageForm4L;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author :keepcleargas
 * @date :2021-04-02 15:14.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSearchForm extends AbstractUserPageForm4L {
    private String name;

    public Map<String, Object> toQueryMap() {
        Map<String, Object> qm = Maps.newHashMapWithExpectedSize(4);
        qm.put("offset", offset);
        qm.put("limit", limit);
        qm.put("name", name);
        // 搜索已上架的商品
        qm.put("status", ProductEntity.Status.LISTED.val());
        return qm;
    }
}
