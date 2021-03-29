package cn.jimoos.service;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.category.BeProductCateCreateForm;
import cn.jimoos.form.category.BeProductCateDeleteForm;
import cn.jimoos.form.category.BeProductCateSearchForm;
import cn.jimoos.form.category.BeProductCateUpdateForm;
import cn.jimoos.model.ProductCategory;
import cn.jimoos.utils.http.Page;

import java.util.List;

/**
 * 商品类别创建
 *
 * @author :keepcleargas
 * @date :2021-03-29 20:12.
 */
public interface ProductCateService {
    /**
     * 创建分类
     *
     * @param form category create form
     * @return ProductCategory
     */
    ProductCategory create(BeProductCateCreateForm form);

    /**
     * 更新分类
     *
     * @param form category update form
     * @return ProductCategory
     * @throws BussException ProductError.CATEGORY_NOT_FOUND
     */
    ProductCategory update(BeProductCateUpdateForm form) throws BussException;

    /**
     * 查询类别
     *
     * @param form product cate search form
     * @return Page<ProductCategory>
     */
    Page<ProductCategory> query(BeProductCateSearchForm form);


    /**
     * 查询 子类别
     *
     * @param pid parent Id
     * @return List<ProductCategory>
     */
    List<ProductCategory> children(Long pid);

    /**
     * 删除类别
     *
     * @param form product cate delete form
     * @return affectNum
     * @throws BussException ProductError.CATEGORY_NOT_FOUND
     */
    int delete(BeProductCateDeleteForm form) throws BussException;


}
