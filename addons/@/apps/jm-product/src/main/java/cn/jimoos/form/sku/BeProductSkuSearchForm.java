package cn.jimoos.form.sku;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import lombok.Data;


/**
 * @author SiletFlower
 * @date 2021/7/20 14:09:53
 * @description
 */
@Data
public class BeProductSkuSearchForm extends AbstractAdminPageForm4L{
    /**
     * SPU ID 商品ID
     */
    private Long productId;
}
