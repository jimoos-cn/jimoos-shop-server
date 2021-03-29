package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.tag.BeProductTagCreateForm;
import cn.jimoos.form.tag.BeProductTagDeleteForm;
import cn.jimoos.form.tag.BeProductTagSearchForm;
import cn.jimoos.form.tag.BeProductTagUpdateForm;
import cn.jimoos.model.ProductTag;
import cn.jimoos.utils.http.Page;

/**
 * @author :keepcleargas
 * @date :2021-03-29 20:59.
 */
public interface ProductTagService {
    /**
     * 创建tag
     *
     * @param form TAG create form
     * @return ProductTag
     */
    ProductTag create(BeProductTagCreateForm form);

    /**
     * 更新tag
     *
     * @param form TAG update form
     * @return ProductTag
     * @throws BussException ProductError.TAG_NOT_FOUND
     */
    ProductTag update(BeProductTagUpdateForm form) throws BussException;

    /**
     * 查询tag
     *
     * @param form product tag search form
     * @return Page<ProductTag>
     */
    Page<ProductTag> query(BeProductTagSearchForm form);


    /**
     * 删除tag
     *
     * @param form product tag delete form
     * @return affectNum
     * @throws BussException ProductError.TAG_NOT_FOUND
     */
    int delete(BeProductTagDeleteForm form) throws BussException;
}
