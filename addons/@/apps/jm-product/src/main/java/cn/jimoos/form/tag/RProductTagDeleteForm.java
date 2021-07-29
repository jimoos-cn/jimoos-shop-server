package cn.jimoos.form.tag;

import cn.jimoos.utils.form.AbstractAdminPageForm4L;
import lombok.Data;

/**
 * @author SiletFlower
 * @date 2021/7/20 13:36:18
 * @description 商品的TAG绑定表单
 */
@Data
public class RProductTagDeleteForm extends AbstractAdminPageForm4L {

    /**
     * 标签ID
     */
    private Long tagId;

    /**
     * 商品ID
     */
    private Long productId;
}
