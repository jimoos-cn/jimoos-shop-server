package cn.jimoos.form.product;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-30 21:16.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeProductSkusForm extends AbstractAdminForm4L {
    private Long productId;
    private List<BeProductForm.SkuInput> skuInputs;
    /**
     * 批量更新
     * true 则删除 重新更新
     * false 只更新 skuInputs 第一个 skuInput 的信息，如果 skuInput#id 为空 则不更新
     */
    private boolean batchUpdate = true;
}
