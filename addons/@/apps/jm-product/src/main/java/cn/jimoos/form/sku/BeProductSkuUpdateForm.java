package cn.jimoos.form.sku;

import cn.jimoos.model.ProductSkuAttrMap;
import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author SiletFlower
 * @date 2021/7/20 14:09:53
 * @description
 */
@Data
public class BeProductSkuUpdateForm extends AbstractAdminPageForm4L{
    /**
     * 关联关系冗余表
     */
    List<ProductSkuAttrMap> attrs;

    /**
     * 销售属性值{attr_value_id},{attr_value_id} 多个销售属性值逗号分隔
     */
    private String attrValueIds;

    /**
     * 商品封面
     */
    private String cover;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 展示价
     */
    private BigDecimal showPrice;

    /**
     * SPU ID 商品ID
     */
    private Long productId;

    /**
     * 商家 ID
     */
    private Long merchantId;

    /**
     * 绑定表ID
     */
    private Long id;
}
