package cn.jimoos.form.category;

import cn.jimoos.utils.form.AbstractAdminForm4L;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * @author keepcleargas
 * @date 2021-03-29 19:52.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BeProductCateUpdateForm extends AbstractAdminForm4L {
    private Long id;

    /**
     * 父id
     */
    private Long pid = 0L;

    /**
     * 商品分类名称
     */
    @NotNull
    private String name;

    /**
     * 商品分类描述
     */
    private String description;

    /**
     * 商品分类图片
     */
    private String imgUrl = "";

    /**
     * 商品排序
     */
    private int sort;
}
