package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.dto.ProductTagDto;
import cn.jimoos.form.tag.*;
import cn.jimoos.model.ProductTag;
import cn.jimoos.utils.http.Page;

import java.util.List;

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

    /**
     * 删除 某商品的绑定值
     *
     * @param productTagDeleteForm 删除表单
     * @return boolean
     */
    boolean deleteBoundValue(RProductTagDeleteForm productTagDeleteForm) throws BussException;

    /**
     * 标签绑定表单
     *
     * @param rProductTagAddForms
     * @return boolean
     */
    boolean addBoundValue(List<RProductTagAddForm> rProductTagAddForms);

    /**
     * 查询某商品的标签
     *
     * @param rProductTagSearchForm
     * @return List<ProductTagDto>
     */
    List<ProductTagDto> queryBoundValue(RProductTagSearchForm rProductTagSearchForm);

    /**
     * 查询某商品未选择的标签
     *
     * @param rProductTagSearchForm
     * @return List<ProductTagDto>
     */
    List<ProductTagDto> queryUnBoundValue(RProductTagSearchForm rProductTagSearchForm);
}
