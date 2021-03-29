package cn.jimoos.form.category;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author keepcleargas
 * @date 2021-03-29 19:52.
 */
@Data
public class BeProductCateCreateForm {

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
    private String description = "";

    /**
     * 商品分类图片
     */
    private String imgUrl = "";

    /**
     * 商品排序
     */
    private int sort = 100;
}
