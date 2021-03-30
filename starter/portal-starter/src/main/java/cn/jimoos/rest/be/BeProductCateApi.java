package cn.jimoos.rest.be;

import cn.jimoos.common.exception.BussException;
import cn.jimoos.form.category.BeProductCateCreateForm;
import cn.jimoos.form.category.BeProductCateDeleteForm;
import cn.jimoos.form.category.BeProductCateSearchForm;
import cn.jimoos.form.category.BeProductCateUpdateForm;
import cn.jimoos.model.ProductCategory;
import cn.jimoos.service.ProductCateService;
import cn.jimoos.utils.http.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author :keepcleargas
 * @date :2021-03-30 11:57.
 */
@RestController
@RequestMapping("/bAdmin/v1/productCategories")
public class BeProductCateApi {
    @Resource
    ProductCateService productCateService;

    /**
     * 查询分类列表
     *
     * @param form Product Cate Search Form
     * @return List<ProductCategory>
     */
    @GetMapping(value = "/query", produces = "application/json; charset=utf-8")
    public Page<ProductCategory> query(@ModelAttribute BeProductCateSearchForm form) {
        return productCateService.query(form);
    }

    /**
     * 查询子分类列表
     *
     * @param pid parent cate id
     * @return List<ProductCategory>
     */
    @GetMapping(value = "/{cateId}/categories", produces = "application/json; charset=utf-8")
    public List<ProductCategory> query(@PathVariable("cateId") Long pid) {
        return productCateService.children(pid);
    }

    /**
     * 添加商品分类
     *
     * @param form Product Cate Form
     * @return ProductCategory
     */
    @PostMapping(produces = "application/json; charset=utf-8")
    public ProductCategory add(@ModelAttribute BeProductCateCreateForm form) {
        return productCateService.create(form);
    }

    /**
     * 更新商品分类
     *
     * @param form Product Cate Update Form
     * @return ProductCategory
     * @throws BussException ProductError.CATEGORY_NOT_FOUND
     */
    @PostMapping(value = "/{id}", produces = "application/json; charset=utf-8")
    public ProductCategory update(@ModelAttribute BeProductCateUpdateForm form) throws BussException {
        return productCateService.update(form);
    }


    /**
     * 删除 分类
     *
     * @param deleteForm product cate delete form
     * @throws BussException ProductError.CATEGORY_NOT_FOUND | ProductError.CATEGORY_USED
     */
    @PostMapping(value = "/{categoryId}/delete", produces = "application/json; charset=utf-8")
    public void deleteCoupon(@ModelAttribute BeProductCateDeleteForm deleteForm) throws BussException {
        productCateService.delete(deleteForm);
    }


}
